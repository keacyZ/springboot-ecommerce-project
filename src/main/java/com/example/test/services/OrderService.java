package com.example.test.services;
import com.example.test.models.Order;
import com.example.test.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public long totalOrders() {
        return orderRepository.count();
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List <Order> getByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public List<Order> getUserOrders(String email){
        return orderRepository.findByAccount_Email(email);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

    public void save(Order order){
        orderRepository.save(order);
    }

    public void updateStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);

    }




}

