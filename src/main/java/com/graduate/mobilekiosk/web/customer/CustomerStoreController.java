package com.graduate.mobilekiosk.web.customerController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerStoreController {

    @PostMapping("store")
    public String moveStore(){
        return"/customer/customer-store.html";
    }
}
