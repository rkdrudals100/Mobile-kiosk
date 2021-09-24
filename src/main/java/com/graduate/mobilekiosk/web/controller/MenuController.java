package com.graduate.mobilekiosk.web.controller;

import com.graduate.mobilekiosk.domain.Category;
import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.repository.CategoryRepository;
import com.graduate.mobilekiosk.repository.MemberRepository;
import com.graduate.mobilekiosk.web.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String menu(Model model, Principal principal) {
        List<Category> categories = categoryRepository.findByUserName(principal.getName());

        model.addAttribute("username", principal.getName());
        model.addAttribute("categories", categories);
        return "seller/menu-management.html";
    }

    @PostMapping("")
    public String categoryAdd(@Validated @ModelAttribute CategoryDto categoryDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:/menus";
        }

        Member member = memberRepository.findByUserId(principal.getName());

        Category category = Category.builder()
                .member(member)
                .name(categoryDto.getCategoryName())
                .userName(principal.getName())
                .build();

        categoryRepository.save(category);

        return "redirect:/menus";
    }
}
