package com.rudkids.core.cart.service;

import com.rudkids.core.cart.domain.Cart;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.exception.CartItemNotFoundException;
import com.rudkids.core.common.fixtures.CartServiceFixtures;
import com.rudkids.core.item.exception.ItemNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CartServiceFailTest extends CartServiceFixtures {

    @DisplayName("[장바구니-아이템추가-ItemNotFoundException]")
    @Test
    void 장바구니에_존재하지_않는_아이템을_추가할_경우_예외가_발생한다() {
        //given, when
        String invalidCartItemName = "invalid";
        CartRequest.AddCartItem invalidRequest = CartRequest.AddCartItem.builder()
                .itemName(invalidCartItemName)
                .amount(2)
                .build();

        //then
        assertThatThrownBy(() -> cartService.addCartItem(user.getId(), invalidRequest))
                .isInstanceOf(ItemNotFoundException.class);
    }

    @DisplayName("[장바구니-아이템수량변경-CartItemNotFoundException]")
    @Test
    void 존재하지_않는_장바구니_아이템의_수량을_변경할_시_예외가_발생한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        UUID invalidCartItemId = UUID.randomUUID();
        CartRequest.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartRequest.UpdateCartItemAmount.builder()
                .cartItemId(invalidCartItemId)
                .amount(3)
                .build();

        //then
        assertThatThrownBy(() -> cartService.updateCartItemAmount(user.getId(), CART_아이템_수량_변경_요청))
                .isInstanceOf(CartItemNotFoundException.class);
    }

    @DisplayName("[장바구니-아이템삭제-CartItemNotFoundException]")
    @Test
    void 존재하지_않는장바구니_아이템을_삭제할_경우_예외가_발생한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        UUID invalidCartItemId = UUID.randomUUID();

        //then
        assertThatThrownBy(() -> cartService.deleteCartItem(user.getId(), invalidCartItemId))
                .isInstanceOf(CartItemNotFoundException.class);
    }
}
