package com.example.test.controller;
import com.example.test.models.Account;
import com.example.test.services.AccountService;
import com.example.test.services.EmailService;
import com.example.test.util.constants.Role;
import com.example.test.util.constants.email.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

    @Controller
    public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Value("${site.domain}")
    private String site_domain;


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute Account account) {
        account.setRole(Role.USER);
        accountService.save(account);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/forgot_password")
    public String forgotPassword() {
        return "forgot_password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam("email") String email,
            RedirectAttributes attributes) {

        Account account = accountService.findByEmail(email);

        if (account == null) {
            attributes.addFlashAttribute("error", "User not found");
            return "redirect:/forgot_password";
        }

        String resetToken = UUID.randomUUID().toString();
        account.setPasswordResetToken(resetToken);
        account.setPassword_reset_token_expiry(LocalDateTime.now().plusMinutes(30));
        accountService.save(account);
        String resetMessage =
                "Reset password link:\n" +
                        site_domain +
                        "change-password?token=" +
                        resetToken;

        EmailDetails emailDetails =
                new EmailDetails(
                        account.getEmail(),
                        resetMessage,
                        "Reset Password");

        emailService.sendSimpleEmail(emailDetails);
        attributes.addFlashAttribute("message", "Password reset link sent to your email");
        return "redirect:/forgot_password";
    }

    @GetMapping("/change-password")
    public String changePasswordPage(
            @RequestParam("token") String token,
            Model model,
            RedirectAttributes attributes) {
        Optional<Account> optionalAccount = accountService.findByToken(token);
        if (optionalAccount.isEmpty()) {
            attributes.addFlashAttribute("tokenError", "Invalid token");
            return "redirect:/forgot_password";
        }
        model.addAttribute("token", token);
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePasswordHandler(
            @RequestParam("token") String token,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes attributes) {
        if (!password.equals(confirmPassword)) {
            attributes.addFlashAttribute("tokenError", "Passwords do not match");
            return "redirect:/change-password?token=" + token;
        }
        Optional<Account> optionalAccount = accountService.findByToken(token);
        if (optionalAccount.isEmpty()) {
            attributes.addFlashAttribute("tokenError", "Invalid token");
            return "redirect:/forgot_password";
        }
        Account account = optionalAccount.get();
        if (account.getPassword_reset_token_expiry().isBefore(LocalDateTime.now())) {
            attributes.addFlashAttribute("tokenError", "Token expired");
            return "redirect:/forgot_password";
        }
        account.setPassword(password);
        account.setPasswordResetToken(null);
        account.setPassword_reset_token_expiry(null);
        accountService.save(account);
        attributes.addFlashAttribute("message", "Password successfully changed");
        return "redirect:/login";
    }
}
