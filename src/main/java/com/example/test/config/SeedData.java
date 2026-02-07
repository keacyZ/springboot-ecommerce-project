package com.example.test.config;
import com.example.test.models.Account;
import com.example.test.models.Authority;
import com.example.test.models.Post;
import com.example.test.services.AccountService;
import com.example.test.services.AuthorityService;
import com.example.test.services.PostService;
import com.example.test.util.constants.Authorities;
import com.example.test.util.constants.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@Slf4j
public class SeedData implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PostService postService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Authorities
        for (Authorities a : Authorities.values()) {
            if (!authorityService.existsByName(a.getAuthority())) {
                Authority authority = new Authority();
                authority.setId(a.getAuthorityId());
                authority.setName(a.getAuthority());
                authorityService.save(authority);
            }
        }

        // Accounts
        createAccountIfNotExists("user@user.com", "user", Role.USER);
        createAccountIfNotExists("admin@admin.com", "admin", Role.ADMIN);
        createAccountIfNotExists("editor@editor.com", "editor", Role.EDITOR);

    }

    private void createAccountIfNotExists(String email, String name, Role role) {
        if (!accountService.existsByEmail(email)) {
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(passwordEncoder.encode("pass123"));
            account.setFirstName(name);
            account.setLastName("lastname");
            account.setRole(role);
            accountService.save(account);
        }
    }
}
