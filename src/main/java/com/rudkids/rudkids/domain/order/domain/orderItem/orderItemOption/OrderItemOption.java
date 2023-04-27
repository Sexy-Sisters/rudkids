package com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOption;

import com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOptionGroup.OrderItemOptionGroup;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_order_item_option")
public class OrderItemOption {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "order_item_option_id")
    private UUID id;

    @JoinColumn(name = "order_item_option_group_id")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orderItemOption")
    private OrderItemOptionGroup orderItemOptionGroup;
    private int ordering;
    private OrderItemOptionName orderItemOptionName;
    private OrderItemOptionPrice orderItemOptionPrice;

    @Builder
    public OrderItemOption(OrderItemOptionGroup orderItemOptionGroup, Integer ordering, OrderItemOptionName orderItemOptionName, OrderItemOptionPrice orderItemOptionPrice) {
        this.orderItemOptionGroup = orderItemOptionGroup;
        this.ordering = ordering;
        this.orderItemOptionName = orderItemOptionName;
        this.orderItemOptionPrice = orderItemOptionPrice;
    }

    public int getOrdering() {
        return ordering;
    }

    public String getOrderItemOptionName() {
        return orderItemOptionName.getValue();
    }

    public int getOrderItemOptionPrice() {
        return orderItemOptionPrice.getValue();
    }
}
