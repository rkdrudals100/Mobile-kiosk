package com.graduate.mobilekiosk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
public class MenuController {

    @GetMapping("/menu-management")
    public String moveMenu(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "seller/menu-management.html";

    }

    @GetMapping("/order-management")
    public String moveOrder(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "seller/order-management.html";

    }

    @GetMapping("/qr")
    public String moveQr(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "seller/qr.html";

    }
}
