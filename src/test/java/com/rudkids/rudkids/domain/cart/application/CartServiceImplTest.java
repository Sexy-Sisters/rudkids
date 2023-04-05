package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.common.fixtures.cart.CartServiceFixtures;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.exception.CartNotFoundException;
import com.rudkids.rudkids.domain.item.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CartServiceImplTest extends CartServiceFixtures {

    @DisplayName("회원의 장바구니는 존재하지 않다가 처음 아이템을 추가할 때 생성한다.")
    @Test
    void 회원의_장바구니는_존재하지_않다가_처음_아이템을_추가할_때_장바구니를_생성한다() {
        //given
        List<Cart> carts = cartRepository.findAll();

        //when
        CartCommand.AddCartItem CART_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .amount(2)
                .build();
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        assertThat(carts).isEmpty();
        cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);
    }

    @DisplayName("장바구니에 아이템을 추가한다.")
    @Test
    void 장바구니에_아이템을_추가한다() {
        //given, when
        CartCommand.AddCartItem CART_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .amount(2)
                .build();
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        Cart findCart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(findCart.getCartItemCount()).isEqualTo(2);
            assertThat(findCart.getCartItems()).hasSize(1);
        });
    }

    @DisplayName("장바구니에 새로운 아이템을 추가한다.")
    @Test
    void 장바구니에_새로운_아이템을_추가한다() {
        //given
        CartCommand.AddCartItem CART_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .amount(2)
                .build();
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        Item newItem = Item.builder()
                .name(Name.create("No.2"))
                .price(Price.create(6_990))
                .quantity(Quantity.create(3_000))
                .itemBio(ItemBio.create("옷 팝니다!"))
                .limitType(LimitType.LIMITED)
                .build();
        itemRepository.save(newItem);

        CartCommand.AddCartItem CART_새로운_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(newItem.getId())
                .amount(4)
                .build();
        cartService.addCartItem(user.getId(), CART_새로운_아이템_요청);

        //then
        Cart findCart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(findCart.getCartItemCount()).isEqualTo(6);
            assertThat(findCart.getCartItems()).hasSize(2);
        });
    }

    @DisplayName("이미 장바구니에 있는 아이템을 추가하면 장바구니아이템 수량만 증가한다.")
    @Test
    void 이미_장바구니에_있는_아이템을_추가하면_장바구니아이템_수량만_증가한다() {
        //given
        CartCommand.AddCartItem CART_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .amount(2)
                .build();
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        Cart findCart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(findCart.getCartItemCount()).isEqualTo(4);
            assertThat(findCart.getCartItems()).hasSize(1);
        });
    }

    @DisplayName("장바구니에 담겨있는 아이템 리스트를 조회한다.")
    @Test
    void 장바구니에_담겨있는_아이템_리스트를_조회한다() {
        //given
        CartCommand.AddCartItem CART_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .amount(2)
                .build();
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        Item newItem = Item.builder()
                .name(Name.create("No.2"))
                .price(Price.create(1_000))
                .quantity(Quantity.create(3_000))
                .itemBio(ItemBio.create("옷 팝니다!"))
                .limitType(LimitType.LIMITED)
                .build();
        itemRepository.save(newItem);

        CartCommand.AddCartItem CART_새로운_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(newItem.getId())
                .amount(4)
                .build();
        cartService.addCartItem(user.getId(), CART_새로운_아이템_요청);

        //when
        CartInfo.Main actual = cartService.findCartItems(user.getId());

        //then
        assertAll(() -> {
            assertThat(actual.totalCartItemPrice()).isEqualTo(9980);
            assertThat(actual.cartItems()).hasSize(2);
        });
    }
}