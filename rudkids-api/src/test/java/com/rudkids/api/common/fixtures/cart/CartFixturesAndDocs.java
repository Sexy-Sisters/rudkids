package com.rudkids.api.common.fixtures.cart;

import com.rudkids.core.cart.dto.CartItemResponse;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.item.domain.ItemStatus;

import java.util.List;
import java.util.UUID;

public class CartFixturesAndDocs {

    public static final String CART_DEFAULT_URL = "/api/v1/cart";
    public static final String 아이템_이름 = "itemName";
    public static final UUID CART_아이템_ID = UUID.randomUUID();
    public static final int 아이템_수량 = 2;
    public static final int CART_아이템_수량 = 3;

    public static final List<CartItemResponse.Main> CART_아이템_리스트 = List.of(
        new CartItemResponse.Main(UUID.randomUUID(), "url", "옷", 1000, 3),
        new CartItemResponse.Main(UUID.randomUUID(), "url", "스티커", 500, 3),
        new CartItemResponse.Main(UUID.randomUUID(), "url", "알약", 990, 3)
    );

    public static CartRequest.AddCartItem CART_아이템_추가_요청() {
        return CartRequest.AddCartItem.builder()
                .itemName(아이템_이름)
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

    public static List<CartItemResponse.Select> CART_선택된_아이템_응답() {
        return List.of(
            new CartItemResponse.Select(아이템_이름, 5000)
        );
    }

    private static List<CartItemResponse.Select> CART_ITEM_선택된_아이템_응답() {
        return List.of(new CartItemResponse.Select(아이템_이름, CART_아이템_수량));
    }
}
