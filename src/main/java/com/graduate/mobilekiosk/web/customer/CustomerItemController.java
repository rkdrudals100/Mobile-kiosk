package com.graduate.mobilekiosk.web.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerProductController {

    @GetMapping("/{item_id}")
    public String moveProduct() {
        return"customer/customer-product.html";
    }
}
