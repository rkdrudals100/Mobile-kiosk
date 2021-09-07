package com.graduate.mobilekiosk.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userId;
    private String password;

    public Member(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    @OneToMany(mappedBy = "member")
    private List<Branch> Branchs = new ArrayList<>();
}
