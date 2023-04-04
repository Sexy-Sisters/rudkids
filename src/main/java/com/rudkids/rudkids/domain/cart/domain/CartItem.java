package com.rudkids.rudkids.domain.cart.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.domain.Item;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_cart_item")
public class CartItem {
    @Id
    @Column(name = "tbl_cart_item", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    protected CartItem() {
    }

    public CartItem(Cart cart, Item item, int count) {
        this.cart = cart;
        this.item = item;
        this.count = count;
    }
}
