package com.graduate.mobilekiosk.web.order;

import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.domain.Order;
import com.graduate.mobilekiosk.domain.OrderItem;
import com.graduate.mobilekiosk.domain.PurchaseType;
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

    // 결제여부 관련 메소드
    public Order purchase(String user) {
        Order order = orderRepository.findByUser(user);
        order.purchase();
        return order;
    }

    // 주문총액 관련 메소드
    public Order updateTotalPrice(String user) {
        Order order = orderRepository.findByUser(user);

        int totalPrice = 0;
        for (OrderItem each: order.getOrderItems()){
            totalPrice = totalPrice + each.getOrderItemPrice() * each.getItemCount();
        }
        order.setTotalPrice(totalPrice);
        return order;
    }


    // OrderItem 관련 메소드
    public Order updateOrderItem(String user, OrderItem orderItem){
        Order order = orderRepository.findByUser(user);

        for(OrderItem each: order.getOrderItems()){
            if(each.getId() == orderItem.getId()){ each.changeOrderItem(orderItem);}
        }
        return order;
    }


    // PurchaseType(매장식사, 포장) 관련 메소드
    public Order selectPurchaseType(String user, PurchaseType purchaseType) {
        Order order = orderRepository.findByUser(user);

        order.setPurchaseType(purchaseType);

        return order;
    }

    public PurchaseType convertTypeOfOrderType(String orderType){
        if (orderType.equals("true")){
            return PurchaseType.EAT;
        } else return PurchaseType.WRAP;
    }
}