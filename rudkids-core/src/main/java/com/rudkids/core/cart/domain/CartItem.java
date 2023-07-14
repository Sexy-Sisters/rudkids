package com.rudkids.core.cart.domain;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_cart_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "cart_item_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String name;

    private String imageUrl;

    private int amount;

    private int price;

    private boolean selected = false;

    @Builder
    public CartItem(Cart cart, Item item, String name, String imageUrl, int amount, int price) {
        this.cart = cart;
        this.item = item;
        this.name = name;
        this.imageUrl = imageUrl;
        this.amount = amount;
        this.price = price;
    }

    public int calculateTotalItemPrice() {
        return price * amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public void updateAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public ItemStatus getItemStatus() {
        return item.getItemStatus();
    }

    public void select() {
        selected = true;
    }

    public boolean isSelectTrue() {
        return selected;
    }
}
