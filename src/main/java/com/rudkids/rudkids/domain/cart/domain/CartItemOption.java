package com.rudkids.rudkids.domain.cart.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CartItemOption {

    @Column(name = "cart_item_option")
    private String value;

    protected CartItemOption() {
    }

    private CartItemOption(String value) {
        this.value = value;
    }

    public static CartItemOption create(String value) {
        return new CartItemOption(value);
    }

    public String getValue() {
        return value;
    }
}
