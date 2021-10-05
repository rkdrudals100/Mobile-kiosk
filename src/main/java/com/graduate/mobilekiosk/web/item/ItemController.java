package com.graduate.mobilekiosk.web.item;

import com.graduate.mobilekiosk.domain.Category;
import com.graduate.mobilekiosk.domain.Item;
import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.web.member.MemberRepository;
import com.graduate.mobilekiosk.web.item.form.CategoryDto;
import com.graduate.mobilekiosk.web.item.form.MenuSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/menus")
@RequiredArgsConstructor
public class ItemController {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final FileStore fileStore;

    @GetMapping("")
    public String menu(Model model, Principal principal) {
        List<Category> categories = categoryRepository.findByUserName(principal.getName());
        model.addAttribute("categories", categories);
        return "seller/menu-management.html";
    }

    @GetMapping("/add-menu")
    public String menuForm(Model model, @RequestParam String category) {
        model.addAttribute("category", category);
        return "seller/menu-form";
    }


    @PostMapping("/add-menu")
    public String menuAdd(@Validated @ModelAttribute MenuSaveDto menuSaveDto, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "redirect:/menus?menu";
        }

        Category findCategory = categoryService.findByCategoryName(menuSaveDto.getCategoryName());
        String image = fileStore.storeFile(menuSaveDto.getImage());
        Item item = Item.builder()
                .category(findCategory)
                .name(menuSaveDto.getName())
                .shortDescription(menuSaveDto.getShortDescription())
                .description(menuSaveDto.getDescription())
                .price(menuSaveDto.getPrice())
                .image(image)
                .build();

        itemRepository.save(item);
        return "redirect:/menus";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @PostMapping("/add-category")
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

}

