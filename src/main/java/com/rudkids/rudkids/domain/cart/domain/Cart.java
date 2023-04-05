package com.rudkids.rudkids.domain.cart.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.user.domain.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_cart")
public class Cart {
    @Id
    @Column(name = "cart_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "cart_item_count")
    private int cartItemCount;

    @OneToMany(mappedBy = "cart")
    private final List<CartItem> cartItems = new ArrayList<>();

    protected Cart() {
    }

    private Cart(User user) {
        this.user = user;
    }

    public static Cart create(User user) {
        return new Cart(user);
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void addCartItemCount(int amount) {
        cartItemCount += amount;
    }

    public int getCartItemCount() {
        return cartItemCount;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getTotalCartItemPrice() {
        return cartItems.stream()
                .mapToInt(CartItem::getCartItemPrice)
                .sum();
    }
}
