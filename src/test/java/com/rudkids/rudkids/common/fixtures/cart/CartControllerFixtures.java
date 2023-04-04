package com.rudkids.rudkids.common.fixtures.cart;

import com.rudkids.rudkids.interfaces.cart.dto.CartRequest;

import java.util.UUID;

public class CartControllerFixtures {

    public static final UUID 아이템_ID = UUID.randomUUID();
    public static final int 아이템_수량 = 2;

    public static CartRequest.AddCartItem CART_아이템_추가_요청() {
        return new CartRequest.AddCartItem(아이템_ID, 아이템_수량);
    }
}
