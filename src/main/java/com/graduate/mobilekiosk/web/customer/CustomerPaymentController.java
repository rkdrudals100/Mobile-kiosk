package com.graduate.mobilekiosk.web.customer;

import com.graduate.mobilekiosk.domain.Order;
import com.graduate.mobilekiosk.domain.WhichPayment;
import com.graduate.mobilekiosk.web.customer.form.GetPaymentFormDto;
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

        model.addAttribute("GetPaymentFormDto", new GetPaymentFormDto());
        model.addAttribute("order", order);



        return "customer/customer-payment";
    }

    @PostMapping("")
    public String payment(HttpServletRequest request, GetPaymentFormDto getPaymentFormDto) {
        String user = request.getSession().getId();

        Order order = orderService.purchase(user);
        log.warn(request.getParameter("requirements"));
        orderService.changeRequirements(user, getPaymentFormDto.getRequirements());
        log.warn("내용 확인: " + getPaymentFormDto.getRequirements() + getPaymentFormDto.getWhichPayment());

        WhichPayment whichPayment = orderService.convertTypeOfWhichPayment(getPaymentFormDto.getWhichPayment().get(0));
        log.warn("whichPayment: " + whichPayment);
        orderService.changeWhichPayment(user, whichPayment);

        return "redirect:/customer/customer-alert";
    }
}
