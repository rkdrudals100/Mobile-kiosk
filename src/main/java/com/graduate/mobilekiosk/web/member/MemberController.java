package com.graduate.mobilekiosk.web.member;

import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.service.MemberService;
import com.graduate.mobilekiosk.web.dto.LoginDto;
import com.graduate.mobilekiosk.web.dto.MemberSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sign-up")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public String sighUpForm(Model model) {
        model.addAttribute("member", new MemberSaveDto());
        return "seller/sign_up.html";
    }

    @PostMapping("")
    public String sighUp(@Validated @ModelAttribute("member") MemberSaveDto memberSaveDto, BindingResult bindingResult) {

        if (memberService.findMember(memberSaveDto.getUsername()) != null) {
            bindingResult.reject("exist", "아이디가 이미 존재합니다.");
        } else if (!memberSaveDto.getPassword().equals(memberSaveDto.getCheckPassword())) {
            bindingResult.reject("diffrentPassword", "패스워드가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "seller/sign_up.html";
        }

        Member saveMember = Member.builder()
                .userId(memberSaveDto.getUsername())
                .password(memberSaveDto.getPassword())
                .storeName(memberSaveDto.getUrl()).build();

        memberService.join(saveMember);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("member") LoginDto loginDto, BindingResult bindingResult) {
        Member findMember = memberService.findMember(loginDto.getUsername());

        if (findMember == null) {
            bindingResult.reject("notexist", "아이디가 존재하지 않습니다.");
        } else if (!findMember.getPassword().equals(loginDto.getPassword())) {
            bindingResult.reject("notpassword", "비밀번호가 틀립니다.");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "seller/index.html";
        }

        return "seller/home_main.html";
    }
}
