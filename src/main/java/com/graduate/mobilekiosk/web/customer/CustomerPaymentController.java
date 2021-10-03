package com.graduate.mobilekiosk.web.customer;

import com.graduate.mobilekiosk.domain.Order;
import com.graduate.mobilekiosk.web.order.OrderItemService;
import com.graduate.mobilekiosk.web.order.OrderRepository;
import com.graduate.mobilekiosk.web.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/customer/payment")
@RequiredArgsConstructor
public class CustomerPaymentController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @GetMapping("")
    public String paymentHome(HttpServletRequest request, Model model) {
        String user = request.getSession().getId();

        Order order = orderRepository.findWithOrderItemByUser(user);
        model.addAttribute("order", order);

        return "customer/customer-payment";
    }

    @PostMapping("")
    public String payment(HttpServletRequest request, Model model) {
        String user = request.getSession().getId();

        Order order = orderService.purchase(user);
        model.addAttribute("order", order);

        return "customer/customer-alert";
    }
}
