package com.example.test.services;
import com.example.test.models.CartItem;
import com.example.test.models.Product;
import com.example.test.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static final String CART_KEY = "cart";

    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_KEY, cart);
        }
        return cart;
    }


    public void addToCart(Long productId, HttpSession session) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return;

        List<CartItem> cart = getCart(session);


        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(productId)) {
                item.increaseQuantity();
                return;
            }
        }

        cart.add(new CartItem(product));
    }

    public void removeFromCart(Long productId, HttpSession session) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getProduct().getId().equals(productId));
    }


    public double getTotalPrice(HttpSession session) {
        double total = 0;
        for (CartItem item : getCart(session)) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }


    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_KEY);
    }
}
