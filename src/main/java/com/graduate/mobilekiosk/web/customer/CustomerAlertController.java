package com.graduate.mobilekiosk.web.customer;

import com.graduate.mobilekiosk.domain.OrderStatus;
import com.graduate.mobilekiosk.web.order.OrderRepository;
import com.graduate.mobilekiosk.web.order.OrderService;
import com.graduate.mobilekiosk.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/customer/customer-alert")
@RequiredArgsConstructor
public class CustomerAlertController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("")
    public String payment(HttpServletRequest request, Model model) {
        String user = request.getSession().getId();

//        if (doubleCheck.equals("done")){
//
//        }

        Order order = orderRepository.findByUser(user);
        model.addAttribute("order", order);

        return "customer/customer-alert";
    }

}
