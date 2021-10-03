package com.graduate.mobilekiosk.web.customer;

import com.graduate.mobilekiosk.domain.Order;
import com.graduate.mobilekiosk.web.order.OrderItemService;
import com.graduate.mobilekiosk.web.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/customer/shopping-basket")
@RequiredArgsConstructor
public class CustomerStoreController {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    @GetMapping("")
    public String shoppingBasket(HttpServletRequest request, Model model) {
        String user = request.getSession().getId();

        Order order = orderRepository.findWithOrderItemByUser(user);
        model.addAttribute("order", order);

        return "customer/customer-store";
    }

    @DeleteMapping("/{orderItemId}")
    public String shoppingBasket(HttpServletRequest request, Model model, @PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);

        return "redirect:";
    }
}
