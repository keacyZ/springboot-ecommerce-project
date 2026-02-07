package com.example.test.repositories;
import com.example.test.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByTitle(String title);
    long count();
}
