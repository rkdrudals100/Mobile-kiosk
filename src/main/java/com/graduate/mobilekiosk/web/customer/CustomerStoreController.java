package com.graduate.mobilekiosk.web.customer;

import com.graduate.mobilekiosk.domain.OrderType;
import com.graduate.mobilekiosk.domain.Order;
import com.graduate.mobilekiosk.domain.OrderItem;
import com.graduate.mobilekiosk.web.order.OrderItemService;
import com.graduate.mobilekiosk.web.order.OrderRepository;
import com.graduate.mobilekiosk.web.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/customer/shopping-basket")
@RequiredArgsConstructor
public class CustomerStoreController {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @GetMapping("")
    public String shoppingBasket(HttpServletRequest request, Model model) {
        String user = request.getSession().getId();

        Order order = orderRepository.findWithOrderItemByUser(user);

        model.addAttribute("order", order);
        model.addAttribute("orderTypes", OrderType.values());

        return "customer/customer-store";
    }

    @PostMapping("")
    public String moveToPayment(HttpServletRequest request){
        String user = request.getSession().getId();
        Order order = orderRepository.findWithOrderItemByUser(user);

        log.warn(request.getParameter("orderTypeCheck"));

        log.warn("폼에서 넘어온 값");

        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem: orderItems) {
            String nameOfOrderItem = "orderItemQuantity" + orderItem.getId();
            orderItem.setItemCount(Integer.parseInt(request.getParameter(nameOfOrderItem)));
            orderService.updateOrderItem(user, orderItem);
        }
        order.setOrderType(OrderType.EAT); // 연관관계의 주인에 함수 만들고 대체

        return "redirect:/customer/payment";

    }

    @DeleteMapping("/{orderItemId}")
    public String shoppingBasket(HttpServletRequest request, Model model, @PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);

        return "redirect:";
    }
}
