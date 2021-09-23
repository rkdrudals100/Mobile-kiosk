package com.graduate.mobilekiosk.service;

import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(String userId) {
        return memberRepository.findByUserId(userId);
    }






}
