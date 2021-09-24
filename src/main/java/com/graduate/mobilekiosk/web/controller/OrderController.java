package com.graduate.mobilekiosk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class OrderController {

    @GetMapping("/order-management")
    public String moveOrder(Model model, Principal principal) {
        return "seller/order-management.html";
    }

}
