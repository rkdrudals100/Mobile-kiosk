package com.graduate.mobilekiosk.repository;


import com.graduate.mobilekiosk.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByUserId(String userId);
}
