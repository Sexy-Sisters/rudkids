package com.rudkids.rudkids.domain.cart.domain;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_cart_item_option_group")
public class CartItemOptionGroup {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "cart_item_option_group_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    @Column(name = "name")
    private String name;

    @Embedded
    private CartItemOption cartItemOption;

    protected CartItemOptionGroup() {
    }

    @Builder
    public CartItemOptionGroup(CartItem cartItem, String name, CartItemOption cartItemOption) {
        this.cartItem = cartItem;
        this.name = name;
        this.cartItemOption = cartItemOption;
    }

    public String getName() {
        return name;
    }

    public String getCartItemOption() {
        return cartItemOption.getValue();
    }
}
