package com.rudkids.rudkids.domain.cart.service;

import com.rudkids.rudkids.common.fixtures.cart.CartServiceFixtures;
import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.exception.CartItemNotFoundException;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
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
        UUID invalidCartItemId = UUID.randomUUID();
        CartCommand.AddCartItem invalidRequest = CartCommand.AddCartItem.builder()
                .itemId(invalidCartItemId)
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

        Cart cart = cartReader.getActiveCart(user);

        UUID invalidCartItemId = UUID.randomUUID();
        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartItemId(invalidCartItemId)
                .amount(3)
                .build();

        //then
        assertThatThrownBy(() -> cartService.updateCartItemAmount(user.getId(), CART_아이템_수량_변경_요청))
                .isInstanceOf(CartItemNotFoundException.class);
    }

    @Disabled("벌크연산 말고 다른 방법을 사용하여 커스텀 예외를 구현할 때까지 이 테스트코드는 보류한다.")
    @DisplayName("[장바구니-아이템선택삭제-CartItemNotFoundException]")
    @Test
    void 존재하지_않는장바구니_아이템들을_선택하여_삭제할_경우_예외가_발생한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        UUID invalidCartItemId = UUID.randomUUID();
        List<UUID> cartItemIds = List.of(invalidCartItemId);

        //then
        CartCommand.DeleteCartItems CART_아이템_삭제_요청 = CartCommand.DeleteCartItems.builder()
                .cartItemIds(cartItemIds)
                .build();

        assertThatThrownBy(() -> cartService.deleteCartItems(user.getId(), CART_아이템_삭제_요청))
                .isInstanceOf(CartItemNotFoundException.class);
    }
}
