package com.example.test.controller;
import com.example.test.models.Account;
import com.example.test.models.CartItem;
import com.example.test.models.Order;
import com.example.test.models.OrderItem;
import com.example.test.services.AccountService;
import com.example.test.services.CartService;
import com.example.test.services.EmailService;
import com.example.test.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.List;



@Controller
public class CheckoutController {
    private final CartService cartService;
    private final OrderService orderService;
    private final EmailService emailService;
    private final AccountService accountService;

    public CheckoutController(
            CartService cartService,
            OrderService orderService,
            EmailService emailService,
            AccountService accountService
    ) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.emailService = emailService;
        this.accountService = accountService;
    }

    @PostMapping("/checkout")
    public String placeOrder(Authentication auth, HttpSession session) {

        if (cartService.getCart(session).isEmpty()) {
            return "redirect:/cart";
        }

        Account account = accountService.findByEmail(auth.getName());
        Order order = new Order();
        order.setAccount(account);
        order.setStatus("HAZIRLANIYOR");
        order.setTotalPrice(cartService.getTotalPrice(session));

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartService.getCart(session)) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(cartItem.getProduct());
            oi.setQuantity(cartItem.getQuantity());
            oi.setPrice(cartItem.getProduct().getPrice());
            orderItems.add(oi);
        }

        order.setItems(orderItems);
        orderService.save(order);
        cartService.clearCart(session);

        return "redirect:/order-success";
    }

    @GetMapping("/order-success")
    public String orderSuccess() {
        return "order_success";
    }

}

