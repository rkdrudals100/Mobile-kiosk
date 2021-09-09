package com.graduate.mobilekiosk.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;
    private String uuid;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
