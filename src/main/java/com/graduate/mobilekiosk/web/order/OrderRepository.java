package com.graduate.mobilekiosk.web.order;

import com.graduate.mobilekiosk.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"orders"})
    Optional<Order> findById(Long id);

    Order findByUser(String user);
}
