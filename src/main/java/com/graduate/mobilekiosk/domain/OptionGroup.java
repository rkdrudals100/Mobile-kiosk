package com.graduate.mobilekiosk.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionGroup {

    @Id
    @GeneratedValue
    @Column(name = "optionGroup_id")
    private Long id;
    private String name;
    private boolean essential;
    private boolean mutilple;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();

    // 연관관계 편의 메소드
    public void addOption(Option option) {
        option.setOptionGroup(this);
        this.getOptions().add(option);
    }
}
