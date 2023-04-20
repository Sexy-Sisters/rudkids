package com.rudkids.rudkids.common.fixtures.cart;

import com.rudkids.rudkids.domain.cart.CartInfo;
import com.rudkids.rudkids.domain.cart.CartItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.interfaces.cart.dto.CartRequest;

import java.util.List;
import java.util.UUID;

public class CartControllerFixtures {

    public static final String CART_DEFAULT_URL = "/api/v1/cart";
    public static final UUID 아이템_ID = UUID.randomUUID();
    public static final UUID CART_ID = UUID.randomUUID();
    public static final UUID CART_아이템_ID = UUID.randomUUID();
    public static final int 아이템_수량 = 2;
    public static final int CART_아이템_수량 = 3;
    public static final int 장바구니_아이템_총_가격 = 9980;
    public static final List<CartItemInfo.Main> 장바구니_아이템 = List.of(
        new CartItemInfo.Main(UUID.randomUUID(), "옷", 1000, optionGroups(), ItemStatus.ON_SALES),
        new CartItemInfo.Main(UUID.randomUUID(), "스티커", 500, optionGroups(), ItemStatus.END_OF_SALES),
        new CartItemInfo.Main(UUID.randomUUID(), "알약", 990, optionGroups(), ItemStatus.ON_SALES)
    );

    private static List<CartItemInfo.CartItemOptionGroup> optionGroups() {
        return List.of(
                new CartItemInfo.CartItemOptionGroup("사이즈", "M")
        );
    }

    public static final List<UUID> CART_아이템_Ids = List.of(CART_아이템_ID, CART_아이템_ID, CART_아이템_ID);

    public static CartRequest.AddCartItem CART_아이템_추가_요청() {
        return CartRequest.AddCartItem.builder()
                .itemId(아이템_ID)
                .optionGroups(List.of(
                       CartRequest.AddCartItemOptionGroup.builder()
                               .name("사이즈")
                               .cartItemOption(new CartRequest.CartItemOption("M", 1000))
                               .build()
                ))
                .amount(아이템_수량)
                .build();
    }

    public static CartRequest.UpdateCartItemAmount CART_아이템_수량_변경_요청() {
        return new CartRequest.UpdateCartItemAmount(CART_ID, CART_아이템_ID, CART_아이템_수량);
    }

    public static CartRequest.DeleteCartItems CART_아이템_선택삭제_변경_요청() {
        return new CartRequest.DeleteCartItems(CART_ID, CART_아이템_Ids);
    }

    public static CartInfo.Main CART_아이템_리스트() {
        return new CartInfo.Main(장바구니_아이템_총_가격, 장바구니_아이템);
    }
}
