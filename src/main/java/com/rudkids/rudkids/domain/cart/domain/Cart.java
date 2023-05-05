package com.rudkids.rudkids.domain.cart.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.DifferentUserException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_cart")
public class Cart extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "cart_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cart")
    private Order order;

    @Column(name = "cart_item_count")
    private int cartItemCount;

    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private final List<CartItem> cartItems = new ArrayList<>();

    private Cart(User user) {
        this.user = user;
        this.cartStatus = CartStatus.ACTIVE;
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

    public CartStatus getCartStatus() {
        return this.cartStatus;
    }

    public int getCartItemCount() {
        return cartItemCount;
    }

    public Order getOrder() {
        return order;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void activate() {
        this.cartStatus = CartStatus.ACTIVE;
    }

    public void deactivate() {
        this.cartStatus = CartStatus.INACTIVE;
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public int calculateTotalPrice() {
        return cartItems.stream()
                .mapToInt(CartItem::calculateTotalItemPrice)
                .sum();
    }

    public void updateCartItemCount(int cartItemCount, int newCartItemCount) {
        this.cartItemCount = (this.cartItemCount - cartItemCount) + newCartItemCount;
    }
}
