package com.graduate.mobilekiosk.web.controller;

import com.graduate.mobilekiosk.domain.Category;
import com.graduate.mobilekiosk.domain.Item;
import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.repository.CategoryRepository;
import com.graduate.mobilekiosk.repository.ItemRepository;
import com.graduate.mobilekiosk.repository.MemberRepository;
import com.graduate.mobilekiosk.web.dto.CategoryDto;
import com.graduate.mobilekiosk.web.dto.MenuSaveDto;
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
public class ItemController {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @GetMapping("")
    public String menu(Model model, Principal principal) {
        List<Category> categories = categoryRepository.findByUserName(principal.getName());
        model.addAttribute("categories", categories);
        return "seller/menu-management.html";
    }

    @PostMapping("")
    public String categoryAdd(@Validated @ModelAttribute CategoryDto categoryDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:/menus?category";
        }

        Member member = memberRepository.findByUserId(principal.getName());

        Category category = Category.builder()
                .member(member)
                .name(categoryDto.getCategoryName())
                .userName(principal.getName())
                .build();

        try {
            categoryRepository.save(category);
        }catch (Exception e) {
            return "redirect:/menus?overlap";
        }

        return "redirect:/menus";
    }

    @PostMapping("add")
    public String menuAdd(@Validated @ModelAttribute MenuSaveDto menuSaveDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:/menus?menu";
        }

        Category findCategory = categoryRepository.findByNameAndUserName(menuSaveDto.getCategoryName(), principal.getName());
        Item item = Item.builder()
                .category(findCategory)
                .name(menuSaveDto.getMenuName())
                .description(menuSaveDto.getDescription())
                .build();

        itemRepository.save(item);
        return "redirect:/menus";
    }
}

