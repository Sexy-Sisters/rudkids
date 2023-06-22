package com.rudkids.core.cart.domain;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private int amount;
    private int price;
    private String imageUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cartItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CartItemOptionGroup> cartItemOptionGroups = new ArrayList<>();

    @Builder
    public CartItem(Cart cart, Item item, int amount, int price, String imageUrl) {
        this.cart = cart;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public int calculateTotalItemPrice() {
        int optionPrice = cartItemOptionGroups.stream()
            .mapToInt(CartItemOptionGroup::getOptionPrice)
            .sum();
        return (price + optionPrice) * amount;
    }

    public void addCartItemOptionGroup(CartItemOptionGroup cartItemOptionGroup) {
        cartItemOptionGroups.add(cartItemOptionGroup);
    }

    public void addAmount(int amount) {
        this.amount += amount;
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
        return item.getEnName();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public List<CartItemOptionGroup> getCartItemOptionGroups() {
        return cartItemOptionGroups;
    }
}