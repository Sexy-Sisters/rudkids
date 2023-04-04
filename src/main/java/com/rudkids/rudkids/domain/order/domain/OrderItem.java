package com.rudkids.rudkids.domain.order.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.domain.Item;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_order_item")
public class OrderItem {

    @Id
    @Column(name = "order_item_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "order_price", nullable = false)
    private int orderPrice;

    @Column(name = "count", nullable = false)
    private int count;

    protected OrderItem() {
    }

    public OrderItem(Order order, Item item, int orderPrice, int count) {
        this.order = order;
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
