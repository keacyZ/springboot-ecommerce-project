package com.example.test.services;
import com.example.test.models.Authority;
import com.example.test.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;



@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public boolean existsByName(String name) {
        return authorityRepository.existsByName(name);
    }

    public Optional<Authority> findById(Long id) {
        return authorityRepository.findById(id);
    }

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }
}
