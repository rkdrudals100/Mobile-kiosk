package com.graduate.mobilekiosk.web;

import com.graduate.mobilekiosk.service.MemberService;
import com.graduate.mobilekiosk.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("member", new LoginDto());
        return "seller/index.html";
    }





}
