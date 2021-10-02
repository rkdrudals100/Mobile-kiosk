package com.graduate.mobilekiosk.web.customer;



import com.graduate.mobilekiosk.domain.Category;
import com.graduate.mobilekiosk.web.item.CategoryRepository;
import com.graduate.mobilekiosk.web.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerHomeController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/{url}")
    public String CustomerHome(@PathVariable String url, Model model){
        List<Category> categories = categoryRepository.findByUserName(url);
        model.addAttribute("categories", categories);

        log.warn("{}", categories);
        return "customer/customer-home.html";
    }

}
