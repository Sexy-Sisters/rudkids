package com.rudkids.rudkids.common.fixtures.cart;

import com.rudkids.rudkids.domain.cart.application.CartInfo;
import com.rudkids.rudkids.domain.cart.application.CartItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.interfaces.cart.dto.CartRequest;

import java.util.List;
import java.util.UUID;

public class CartControllerFixtures {

    public static final String CART_DEFAULT_URL = "/api/v1/cart";
    public static final UUID 아이템_ID = UUID.randomUUID();
    public static final int 아이템_수량 = 2;
    public static final int 장바구니_아이템_총_가격 = 9980;
    public static final List<CartItemInfo.Main> 장바구니_아이템 = List.of(
            new CartItemInfo.Main(UUID.randomUUID(),"옷", 1000, ItemStatus.IN_STOCK),
            new CartItemInfo.Main(UUID.randomUUID(),"스티커", 500, ItemStatus.SOLD_OUT),
            new CartItemInfo.Main(UUID.randomUUID(),"알약", 990, ItemStatus.IN_STOCK)
    );

    public static CartRequest.AddCartItem CART_아이템_추가_요청() {
        return new CartRequest.AddCartItem(아이템_ID, 아이템_수량);
    }

    public static CartInfo.Main CART_아이템_리스트() {
        return new CartInfo.Main(장바구니_아이템_총_가격, 장바구니_아이템);
    }
}
