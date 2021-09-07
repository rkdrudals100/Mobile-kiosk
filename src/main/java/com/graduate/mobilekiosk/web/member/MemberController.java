package com.graduate.mobilekiosk.web.member;

import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.service.MemberService;
import com.graduate.mobilekiosk.web.dto.SignForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService service;

    @PostMapping("/sign-up")
    public String sighUp(@ModelAttribute("form") SignForm signForm) {

        log.warn("아이디 {}, 비번1 {}, 비번2 {}", signForm.getUserId(), signForm.getPassword(), signForm.getPasswordCheck());
        if (service.findMember(signForm.getUserId()) != null) {
            log.warn("이미 있는 아이디 확인 testtest");
            return "seller/sign_up.html";
        }

        if (!(signForm.getPassword().equals(signForm.getPasswordCheck()))) {
            log.warn("비밀번호 틀림");
            return "seller/sign_up.html";
        }

        Member member = new Member(signForm.getUserId(), signForm.getPassword());
        service.join(member);

        return "seller/index.html";

    }


}
