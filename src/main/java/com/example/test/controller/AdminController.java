package com.example.test.controller;
import com.example.test.services.AccountService;
import com.example.test.services.OrderService;
import com.example.test.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AccountService accountService;
    private final PostService postService;
    private final OrderService orderService;

    public AdminController(AccountService accountService, PostService postService, OrderService orderService) {
        this.accountService = accountService;
        this.postService = postService;
        this.orderService = orderService;
    }

    @GetMapping
    public String adminRoot(Model model) {

        model.addAttribute("totalUsers", accountService.countAll());
        model.addAttribute("activeUsers", accountService.countActive());
        model.addAttribute("totalPosts", postService.countAll());
        model.addAttribute("recentUsers", accountService.findRecentUsers());
        model.addAttribute("totalOrders", orderService.totalOrders());
        return "admin/admin_dashboard";
    }
}
