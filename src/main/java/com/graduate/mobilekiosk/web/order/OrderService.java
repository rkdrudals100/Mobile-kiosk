package com.graduate.mobilekiosk.web.order;

import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.domain.Order;
import com.graduate.mobilekiosk.domain.OrderItem;
import com.graduate.mobilekiosk.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public void save(Order order1) {
        long count = orderRepository.count();
        log.warn("count: =========== {}",count);
        order1.setOrderNum(count+1);
        orderRepository.save(order1);
    }

    public Order createOrder(String user, String url) {
        Order order = orderRepository.findByUser(user);

        if (order == null) {
            Member member = memberRepository.findByUserId(url);
            Order newOrder = Order.builder()
                    .member(member)
                    .user(user)
                    .build();
            return orderRepository.save(newOrder);
        }
        return order;
    }

    public Order purchase(String user) {
        Order order = orderRepository.findByUser(user);
        order.purchase();
        return order;
    }

    public Order updateOrderItem(String user, OrderItem orderItem){
        Order order = orderRepository.findByUser(user);

        for(OrderItem each: order.getOrderItems()){
            if(each.getId() == orderItem.getId()){ each.updateOrderItem(orderItem);}
        }
        return order;
    }
}
