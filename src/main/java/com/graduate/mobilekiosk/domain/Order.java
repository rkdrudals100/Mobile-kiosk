package com.graduate.mobilekiosk.domain;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @Table(name = "orders")
@Data
@ToString(callSuper = true, exclude = {"orderItems", "member"})
@NoArgsConstructor
@AllArgsConstructor
@Builder @Accessors(chain = true)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue()
    @Column(name = "order_id")
    private Long id;

    private int totalPrice;

    private String user;

    private String purchase;

    private String requirements;

    @Enumerated(EnumType.STRING)
    private WhichPayment whichPayment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private PurchaseType purchaseType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long orderNum;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }

    public void purchase() {
        this.orderDate = new Date();
        this.purchase = "purchase";
    }

}
