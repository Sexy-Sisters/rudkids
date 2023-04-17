package com.rudkids.rudkids.domain.order.domain.orderItem;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_order_item_option_group")
public class OrderItemOptionGroup {

    @Id
    @Column(name = "order_item_option_group_id")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @JoinColumn(name = "order_item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderItem orderItem;
    private Integer ordering;
    private String itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItemOptionGroup", cascade = CascadeType.PERSIST)
    private List<OrderItemOption> orderItemOptions = new ArrayList<>();

    public Long calculateTotalAmount() {
        return orderItemOptions.stream()
            .mapToLong(OrderItemOption::getItemOptionPrice)
            .sum();
    }

    @Builder
    public OrderItemOptionGroup(OrderItem orderItem, Integer ordering, String itemOptionGroupName) {
        this.orderItem = orderItem;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }
}
