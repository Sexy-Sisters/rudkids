package com.rudkids.rudkids.domain.order.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.domain.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_order_item")
public class OrderItem {

    @Id
    @Column(name = "order_item_id")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "order_price", nullable = false)
    private int itemPrice;

    @Column(name = "count", nullable = false)
    private int orderCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItem", cascade = CascadeType.PERSIST)
    private List<OrderItemOptionGroup> orderItemOptionGroups = new ArrayList<>();

    public OrderItem(Order order, Item item, int itemPrice, int orderCount) {
        this.order = order;
        this.item = item;
        this.itemPrice = itemPrice;
        this.orderCount = orderCount;
    }

    public long calculateTotalAmount() {
        var itemOptionTotalAmount = orderItemOptionGroups.stream()
            .mapToLong(OrderItemOptionGroup::calculateTotalAmount)
            .sum();
        return (itemPrice + itemOptionTotalAmount) * orderCount;
    }
}
