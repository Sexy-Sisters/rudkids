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

    private int count;

    @OneToMany(mappedBy = "cart")
    private final List<CartItem> cartItems = new ArrayList<>();

    protected Cart() {
    }

    public Cart(User user, int count) {
        this.user = user;
        this.count = count;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }
}
