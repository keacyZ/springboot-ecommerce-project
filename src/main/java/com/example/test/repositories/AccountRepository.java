package com.example.test.repositories;
import com.example.test.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;



@Repository

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmailIgnoreCase(String email);

    Optional<Account> findByPasswordResetToken(String token);

    Optional<Account> findByEmail (String email);

    boolean existsByEmailIgnoreCase(String email);


    boolean existsByEmail(String email);


    long countByActiveTrue();

        long count();

        List<Account> findTop5ByOrderByIdDesc();


}
