package com.example.test.controller;
import com.example.test.models.Account;
import com.example.test.services.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
import java.util.Optional;



@Controller
public class ProfileController {

    private final AccountService accountService;

    public ProfileController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {

        String authUser = principal.getName();

        Account user = accountService.findByEmail(authUser);


        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile/update")
    @PreAuthorize("isAuthenticated()")
    public String updateProfile(@ModelAttribute Account formUser, Principal principal) {

        String email = principal.getName();
        Account account = accountService.findByEmail(email);

        account.setFirstName(formUser.getFirstName());
        account.setLastName(formUser.getLastName());
        account.setPhone(formUser.getPhone());

        accountService.save(account);

        return "redirect:/profile";
    }
}

