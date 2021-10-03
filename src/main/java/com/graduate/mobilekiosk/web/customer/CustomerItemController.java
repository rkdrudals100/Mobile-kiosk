package com.graduate.mobilekiosk.web.customer;

import com.graduate.mobilekiosk.domain.Category;
import com.graduate.mobilekiosk.domain.Item;
import com.graduate.mobilekiosk.domain.Order;
import com.graduate.mobilekiosk.web.item.ItemRepository;
import com.graduate.mobilekiosk.web.order.OrderItemService;
import com.graduate.mobilekiosk.web.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerItemController {

    private final ItemRepository itemrepository;
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @GetMapping("items/{itemId}")
    public String moveItem(@PathVariable Long itemId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Item item = itemrepository.findById(itemId).get();
        model.addAttribute("item", item);

        return"customer/customer-item.html";
    }

    @PostMapping("items/{itemId}")
    public String customerAdd(@PathVariable Long itemId, Model model, HttpServletRequest request, @RequestParam String url) {
        String user = request.getSession().getId();

        Order order = orderService.createOrder(user, url);
        orderItemService.createOrderItem(order, itemId);

        return "redirect:/customer/" + url;
    }

//    @PostMapping("{url}")
//    public String storedItem(@PathVariable String url, Model model){
//
//
//    }
}