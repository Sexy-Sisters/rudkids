package com.rudkids.api.common.fixtures;

import com.rudkids.core.cart.dto.CartItemResponse;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.dto.CartResponse;
import com.rudkids.core.item.domain.ItemStatus;

import java.util.List;
import java.util.UUID;

public class CartControllerFixtures {

    public static final String CART_DEFAULT_URL = "/api/v1/cart";
    public static final UUID 아이템_ID = UUID.randomUUID();
    public static final UUID CART_아이템_ID = UUID.randomUUID();
    public static final int 아이템_수량 = 2;
    public static final int CART_아이템_수량 = 3;
    public static final int 장바구니_아이템_총_가격 = 9980;
    public static final List<CartItemResponse.Main> 장바구니_아이템 = List.of(
        new CartItemResponse.Main(UUID.randomUUID(), "옷", "url", 1000, 3,  optionGroups(), ItemStatus.SELLING),
        new CartItemResponse.Main(UUID.randomUUID(), "스티커", "url", 500, 3, optionGroups(), ItemStatus.SOLD_OUT),
        new CartItemResponse.Main(UUID.randomUUID(), "알약", "url", 990, 3, optionGroups(), ItemStatus.SELLING)
    );

    private static List<CartItemResponse.MainOptionGroup> optionGroups() {
        return List.of(
                new CartItemResponse.MainOptionGroup("사이즈", "M")
        );
    }

    public static final List<UUID> CART_아이템_Ids = List.of(CART_아이템_ID, CART_아이템_ID, CART_아이템_ID);

    public static CartRequest.AddCartItem CART_아이템_추가_요청() {
        return CartRequest.AddCartItem.builder()
                .itemId(아이템_ID)
                .optionGroups(List.of(
                       CartRequest.AddCartItemOptionGroup.builder()
                               .name("사이즈")
                               .option(new CartRequest.AddCartItemOption("M", 1000))
                               .build()
                ))
                .amount(아이템_수량)
                .build();
    }

    public static CartRequest.UpdateCartItemAmount CART_아이템_수량_변경_요청() {
        return new CartRequest.UpdateCartItemAmount(CART_아이템_ID, CART_아이템_수량);
    }

    public static CartRequest.DeleteCartItems CART_아이템_선택삭제_변경_요청() {
        return new CartRequest.DeleteCartItems(CART_아이템_Ids);
    }

    public static CartResponse.Main CART_아이템_리스트() {
        return new CartResponse.Main(장바구니_아이템_총_가격, 장바구니_아이템);
    }
}
