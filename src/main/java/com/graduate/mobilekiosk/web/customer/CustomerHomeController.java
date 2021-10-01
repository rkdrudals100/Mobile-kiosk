package com.graduate.mobilekiosk.web.customerController;


import com.graduate.mobilekiosk.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/customer")
public class CustomerHomeController {

    @GetMapping("")
    public String CustomerHome(){
        return "customer/customer-home.html";
    }

}
