package com.graduate.mobilekiosk.domain;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"order", "item"})
@AllArgsConstructor
@Builder @Accessors(chain = true)
public class OrderItem extends BaseEntity {


    @Id
    @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;
    private int orderPrice;
    private int count1;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    public String toString() {
        return "OrderItem(super=" + super.toString() + ", id=" + this.getId() + ", orderPrice=" + this.getOrderPrice() + ", count1=" + this.getCount1() + ", description=" + this.getDescription() + ", order=" + this.getOrder() + ", item=" + this.getItem() + ")";
    }
}
