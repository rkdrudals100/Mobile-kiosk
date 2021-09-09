package com.graduate.mobilekiosk.web.member;

import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.domain.Store;
import com.graduate.mobilekiosk.service.MemberService;
import com.graduate.mobilekiosk.web.dto.MemberSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/sign-up")
    public String sighUpForm(Model model) {
        model.addAttribute("member", new MemberSaveDto());
        return "seller/sign_up.html";
    }

    @PostMapping("/sign-up")
    public String sighUp(@Validated @ModelAttribute("member") MemberSaveDto memberSaveDto, BindingResult bindingResult) {

        if (!memberSaveDto.getPassword().equals(memberSaveDto.getCheckPassword())) {
            bindingResult.reject("diffrentPassword","패스워드가 같지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "seller/sign_up.html";
        }

        Member saveMember = new Member(memberSaveDto.getUsername(), memberSaveDto.getPassword());
        memberService.join(saveMember);
        return "redirect:/";

    }


}
