package com.graduate.mobilekiosk.web.order;

import com.graduate.mobilekiosk.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
