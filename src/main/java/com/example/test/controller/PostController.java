package com.example.test.controller;
import com.example.test.models.Account;
import com.example.test.models.Post;
import com.example.test.services.AccountService;
import com.example.test.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Optional;



@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal) {

        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isEmpty()){
            return "404";
        }
        Post post = optionalPost.get();
        model.addAttribute("post", post);

        boolean isOwner = false;

        if(principal != null){
            String authUser = principal.getName();
            if(authUser.equals(post.getAccount().getEmail())){
                isOwner = true;
            }
        }
        model.addAttribute("isOwner", isOwner);
        return "post_views/post";
    }

    @GetMapping("/add_post")
    @PreAuthorize("isAuthenticated()")
    public String addPost(Model model, Principal principal){

        String authUser = principal.getName();

        Account account = accountService.findByEmail(authUser);

        if (account == null) {
            return "redirect:/login";
        }

        Post post = new Post();
        post.setAccount(account);

        model.addAttribute("post", post);
        return "post_views/post_add";
    }

    @PostMapping("/post_add")
    @PreAuthorize("isAuthenticated()")
    public String addPostHandler(@ModelAttribute Post post, Principal principal){

        String authUser = principal.getName();

        Account account = accountService.findByEmail(authUser);

        if (account == null) {
            return "redirect:/login";
        }

        post.setAccount(account);

        postService.save(post);

        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model){

        Optional<Post> optionalPost = postService.getById(id);

        if(optionalPost.isEmpty()){
            return "404";
        }

        model.addAttribute("post", optionalPost.get());
        return "post_views/post_edit";
    }

    @PostMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id,
                             @ModelAttribute Post post){

        Optional<Post> optionalPost = postService.getById(id);

        if(optionalPost.isPresent()){
            Post existingPost = optionalPost.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postService.save(existingPost);
        }

        return "redirect:/post/" + id;
    }

    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deletePost(@PathVariable Long id){

        Optional<Post> optionalPost = postService.getById(id);

        if(optionalPost.isPresent()){
            postService.delete(optionalPost.get());
        }

        return "redirect:/";
    }
}
