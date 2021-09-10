package com.graduate.mobilekiosk.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "options")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Option {
    @Id
    @GeneratedValue
    @Column(name = "option_id")
    private Long id;

    private String name;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optionGroup_id")
    private OptionGroup optionGroup;

}
