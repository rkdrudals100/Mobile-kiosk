package com.graduate.mobilekiosk.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Branch {

    @Id
    @GeneratedValue
    private Long id;
    private String branchName;
    private String branchURL;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
