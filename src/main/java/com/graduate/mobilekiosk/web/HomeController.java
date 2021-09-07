package com.graduate.mobilekiosk.web;

import com.graduate.mobilekiosk.service.MemberService;
import com.graduate.mobilekiosk.web.dto.SignForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.warn("test");
        return "seller/index.html";
    }

    @GetMapping("/sign-up")
    public String sighUp(Model model) {
        log.debug("test");
        model.addAttribute("form", new SignForm());
        return "seller/sign_up.html";
    }



}
