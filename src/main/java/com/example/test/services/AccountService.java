package com.example.test.services;
import com.example.test.models.Account;
import com.example.test.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;



@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public List<Account> findAll() {
        return accountRepository.findAll();
    }


    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account bulunamadı: " + email));
    }


    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    public Optional<Account> findByToken(String token) {
        return accountRepository.findByPasswordResetToken(token);
    }
    public long count() {
        return accountRepository.count();
    }

    public long countAll(){
        return accountRepository.count();
    }


    public long countActiveUsers(){
        return accountRepository.countByActiveTrue();
    }

    public long getTotalUsers() {
        return accountRepository.count();
    }

    public List<Account> getRecentUsers() {
        return accountRepository.findTop5ByOrderByIdDesc();
    }

    public long countActive() {
        return accountRepository.countByActiveTrue();
    }

    public List<Account> findRecentUsers() {
        return accountRepository.findTop5ByOrderByIdDesc();
    }



}

