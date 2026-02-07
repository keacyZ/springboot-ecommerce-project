package com.example.test.repositories;
import com.example.test.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByAccount_Email(String email);
    List<Order> findByUsername(String username);
    long count();
}
