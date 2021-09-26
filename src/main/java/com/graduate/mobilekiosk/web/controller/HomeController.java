package com.graduate.mobilekiosk.web.controller;

import com.graduate.mobilekiosk.service.MemberService;
import com.graduate.mobilekiosk.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    @GetMapping("")
    public String home() {
        return "seller/home-main.html";

    }





}
