package com.rudkids.rudkids.domain.cart.domain;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_cart_item")
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

    private int amount;
    private int price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cartItem", cascade = CascadeType.ALL)
    private final List<CartItemOptionGroup> cartItemOptionGroups = new ArrayList<>();

    protected CartItem() {
    }

    @Builder
    public CartItem(Cart cart, Item item, int amount, int price) {
        this.cart = cart;
        this.item = item;
        this.amount = amount;
        this.price = price;
    }

    public void addCartItemOptionGroup(CartItemOptionGroup cartItemOptionGroup) {
        cartItemOptionGroups.add(cartItemOptionGroup);
    }

    public void addPrice(int price) {
        this.price += price;
    }

    public int getCartItemPrice() {
        return price * amount;
    }

    public void updateAmount(int amount) {
        this.amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return item.getName();
    }

    public int getPrice() {
        return price;
    }

    public ItemStatus getItemStatus() {
        return item.getItemStatus();
    }

    public int getAmount() {
        return amount;
    }

    public List<CartItemOptionGroup> getCartItemOptionGroups() {
        return cartItemOptionGroups;
    }
}
