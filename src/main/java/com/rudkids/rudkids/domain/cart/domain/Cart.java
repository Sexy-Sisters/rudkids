package com.rudkids.rudkids.domain.cart.domain;

import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.DifferentUserException;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_cart")
public class Cart {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "cart_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "cart_item_count")
    private int cartItemCount;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
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

    public UUID getId() {
        return id;
    }

    public int getCartItemCount() {
        return cartItemCount;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public int getTotalCartItemPrice() {
        return cartItems.stream()
                .mapToInt(CartItem::getCartItemPrice)
                .sum();
    }

    public void updateCartItemCount(int cartItemCount, int newCartItemCount) {
        this.cartItemCount = (this.cartItemCount - cartItemCount) + newCartItemCount;
    }
}
