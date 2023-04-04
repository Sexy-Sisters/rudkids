package com.rudkids.rudkids.domain.cart.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.domain.Item;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name = "tbl_cart_item")
public class CartItem {
    @Id
    @Column(name = "tbl_cart_item_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int amount;

    protected CartItem() {
    }

    @Builder
    public CartItem(Cart cart, Item item, int amount) {
        this.cart = cart;
        this.item = item;
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }
}
