package com.example.test.services;
import com.example.test.models.Post;
import com.example.test.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    public boolean existsByTitle(String title) {
        return postRepository.existsByTitle(title);
    }

    public  Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
    public long count() {
        return postRepository.count();
    }
    public long countAll(){
        return postRepository.count();
    }

    public long getTotalPosts() {
        return postRepository.count();
    }


}
