package com.graduate.mobilekiosk.web.order;

import com.graduate.mobilekiosk.domain.*;
import com.graduate.mobilekiosk.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
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

        for(OrderItem each : order.getOrderItems()){
            if(each.getId() == orderItem.getId()){ each.changeOrderItem(orderItem);}
        }
        return order;
    }


    public Boolean checkOrderItem(String user, Long itemId){
        Order order = orderRepository.findByUser(user);

        try {
            for (OrderItem each : order.getOrderItems()) {
                log.warn("확인: "+ each.getItem());
                if (each.getItem().getId() == itemId){
                    return Boolean.FALSE;}
            }
            return Boolean.TRUE;
        }catch (Exception e){}

        return Boolean.TRUE;
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

    // 요구사항 관련 메소드
    public Order changeRequirements(String user, String requirements){
        Order order = orderRepository.findByUser(user);
        order.setRequirements(requirements);

        return order;
    }

    // 결제수단 관련 메소드
    public WhichPayment convertTypeOfWhichPayment(String whichPayment){
        if (whichPayment.equals("card")){return WhichPayment.CARD;
        }else if (whichPayment.equals("cash")){return WhichPayment.CASH;
        }else if (whichPayment.equals("kakaoPay")){return WhichPayment.KAKAOPAY;
        }else if (whichPayment.equals("naverPay")){return WhichPayment.NAVERPAY;
        }else {log.warn("결제수단에 예기치 못한 값이 들어왔습니다");
        return null;}
    }

    public Order changeWhichPayment(String user, WhichPayment whichPayment){
        Order order = orderRepository.findByUser(user);
        order.setWhichPayment(whichPayment);

        return order;
    }
}