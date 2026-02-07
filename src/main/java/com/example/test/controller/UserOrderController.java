package com.example.test.controller;
import com.example.test.services.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class UserOrderController {

    private final OrderService orderService;

    public UserOrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public String myOrders(Model model,
                           @AuthenticationPrincipal UserDetails user){

        model.addAttribute("orders",
                orderService.getByUsername(user.getUsername()));

        return "orders";
    }
}
