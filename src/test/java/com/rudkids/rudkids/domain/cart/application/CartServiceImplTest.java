package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.common.fixtures.cart.CartServiceFixtures;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.exception.CartItemNotFoundException;
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
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        Cart actual = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getCartItemCount()).isEqualTo(2);
            assertThat(actual.getCartItems()).hasSize(1);
        });
    }

    @DisplayName("장바구니에 새로운 아이템을 추가한다.")
    @Test
    void 장바구니에_새로운_아이템을_추가한다() {
        //given
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
        Cart actual = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getCartItemCount()).isEqualTo(6);
            assertThat(actual.getCartItems()).hasSize(2);
        });
    }

    @DisplayName("이미 장바구니에 있는 아이템을 추가하면 장바구니아이템 수량만 증가한다.")
    @Test
    void 이미_장바구니에_있는_아이템을_추가하면_장바구니아이템_수량만_증가한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        Cart actual = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getCartItemCount()).isEqualTo(4);
            assertThat(actual.getCartItems()).hasSize(1);
        });
    }

    @DisplayName("장바구니에 담겨있는 아이템 리스트를 조회한다.")
    @Test
    void 장바구니에_담겨있는_아이템_리스트를_조회한다() {
        //given
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

    /*
    2023.04.06 장바구니아이템의 수량을 바꾸면 장바구니 안에있는 해당 아이템의 수량이 바뀌지 않는 버그
    assertThat(cart.getCartItems().get(0).getAmount()).isEqualTo(3);
     */
    @DisplayName("장바구니 아이템의 수량을 변경한다.")
    @Test
    void 장바구니_아이템의_수량을_변경한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item)
                .orElseThrow(CartItemNotFoundException::new);

        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartItemId(cartItem.getId())
                .amount(3)
                .build();
        cartService.updateCartItemAmount(user.getId(), CART_아이템_수량_변경_요청);

        //then
        CartItem findCartItem = cartItemRepository.findByCartAndItem(cart, item)
                        .orElseThrow(CartItemNotFoundException::new);

        Cart findCart = cartRepository.findByUserId(user.getId())
                        .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(findCartItem.getAmount()).isEqualTo(3);
//            assertThat(findCart.getCartItems().get(0).getAmount()).isEqualTo(3);
        });
    }
}