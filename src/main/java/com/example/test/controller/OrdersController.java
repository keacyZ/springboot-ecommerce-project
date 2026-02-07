package com.example.test.controller;
import com.example.test.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class OrdersController {
    private final OrderService orderService;
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/my-orders")
    public String myOrders(Authentication auth, Model model){

        var orders = orderService.getUserOrders(auth.getName());
        model.addAttribute("orders", orders);

        return "my_orders";
    }
}
