package com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOptionGroup;

import com.rudkids.rudkids.domain.order.domain.orderItem.OrderItem;
import com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOption.OrderItemOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_order_item_option_group")
public class OrderItemOptionGroup {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "order_item_option_group_id")
    private UUID id;

    @JoinColumn(name = "order_item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderItem orderItem;

    private Integer ordering;
    private String itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItemOptionGroup", cascade = CascadeType.PERSIST)
    private List<OrderItemOption> orderItemOptions = new ArrayList<>();

    public int calculateTotalAmount() {
        return orderItemOptions.stream()
            .mapToInt(OrderItemOption::getOrderItemOptionPrice)
            .sum();
    }

    @Builder
    public OrderItemOptionGroup(OrderItem orderItem, Integer ordering, String itemOptionGroupName) {
        this.orderItem = orderItem;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }

    public void addOrderItemOption(OrderItemOption orderItemOption) {
        this.orderItemOptions.add(orderItemOption);
    }
}
