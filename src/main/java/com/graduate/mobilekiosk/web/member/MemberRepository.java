package com.graduate.mobilekiosk.web.member;


import com.graduate.mobilekiosk.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUserId(String userId);
}
