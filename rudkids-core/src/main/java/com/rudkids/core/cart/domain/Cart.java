package com.rudkids.core.cart.domain;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.exception.DifferentUserException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "cart_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private final List<CartItem> cartItems = new ArrayList<>();

    private Cart(User user) {
        this.user = user;
    }

    public static Cart create(User user) {
        return new Cart(user);
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public UUID getId() {
        return id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public List<CartItem> getSelectedCartItems() {
        return cartItems.stream()
            .filter(CartItem::isSelected)
            .toList();
    }

    public int calculateSelectedCartItemsTotalPrice() {
        return cartItems.stream()
            .filter(CartItem::isSelected)
            .mapToInt(CartItem::calculateTotalItemPrice)
            .sum();
    }

    public void initSelect() {
        for(CartItem cartItem: cartItems) {
            cartItem.unSelect();
        }
    }
}
