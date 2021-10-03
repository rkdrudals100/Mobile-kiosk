package com.graduate.mobilekiosk.web.order;

import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.domain.Order;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"orders"})
    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = {"orderItems", "orderItems.item"}, type = EntityGraph.EntityGraphType.LOAD)
    Order findWithOrderItemByUser(String user);

    Order findByUser(String user);

    List<Order> findByMemberAndPurchase(Member id, String purchase);
}
