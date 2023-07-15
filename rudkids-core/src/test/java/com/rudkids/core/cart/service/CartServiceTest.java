package com.rudkids.core.cart.service;

import com.rudkids.core.cart.domain.Cart;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.dto.CartResponse;
import com.rudkids.core.cart.exception.CartItemNotFoundException;
import com.rudkids.core.common.fixtures.cart.CartServiceFixtures;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.item.exception.ItemNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CartServiceTest extends CartServiceFixtures {

    @Nested
    @DisplayName("장바구니를 생성한다")
    class createCart {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            List<Cart> carts = jpaCartRepository.findAll();

            //when
            //유저는 장바구니를 가지고 있지 않다가 아이템을 장바구니에 넣으려고 하면 생성된다
            cartService.addCartItem(user.getId(), CART_아이템_요청);

            //then
            List<Cart> actual = jpaCartRepository.findAll();
            assertThat(carts).isEmpty();
            assertThat(actual).hasSize(1);
        }
    }

    @Nested
    @DisplayName("장바구니에 아이템을 추가한다")
    class addCartItem {

        @Test
        @DisplayName("성공")
        void success() {
            //given, when
            var cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

            //then
            Cart cart = cartRepository.get(user);
            var cartItem = cartItemRepository.get(cartItemId);

            assertAll(() -> {
                assertThat(cart.getCartItems()).hasSize(1);
                assertThat(cartItem.getAmount()).isEqualTo(2);
            });
        }

        @Test
        @DisplayName("성공: 옵션이 붙은 아이템을 장바구니에 추가하면 가격에 옵션가격이 붙는다")
        void success2() {
            //given, when
            UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

            //then
            var cartItem = cartItemRepository.get(cartItemId);

            assertThat(cartItem.calculateTotalItemPrice()).isEqualTo(9_000);
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() {
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
    }

    @Nested
    @DisplayName("장바구니에 아이템을 추가하고 새로운 아이템을 또 추가한다")
    class addNewCartItem {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            Item newItem = Item.builder()
                .name(Name.create("No.2", "남바투"))
                .price(Price.create(6_990))
                .quantity(Quantity.create(3_000))
                .itemBio(ItemBio.create("옷 팝니다!"))
                .limitType(LimitType.LIMITED)
                .build();

            ItemImage image = ItemImage.create(newItem, "path", "url");
            newItem.addImage(image);
            itemRepository.save(newItem);

            //새로운 아이템요청
            CartRequest.AddCartItem CART_새로운_아이템_요청 = CartRequest.AddCartItem.builder()
                .itemName(newItem.getEnName())
                .optionGroups(List.of(
                    CartRequest.AddCartItemOptionGroup.builder()
                        .name("사이즈")
                        .option(new CartRequest.AddCartItemOption("M", 1000))
                        .build()))
                .amount(4)
                .build();
            cartService.addCartItem(user.getId(), CART_새로운_아이템_요청);

            //then
            Cart cart = cartRepository.get(user);
            assertThat(cart.getCartItems()).hasSize(2);
        }

        @Test
        @DisplayName("성공: 같은 아이템이지만 옵션값이 다를 경우 장바구니에 추가하면 새롭게 추가된다")
        void success2() {
            //given
            cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            //같은 아이템을 요청하지만 옵션값이 다르다
            CartRequest.AddCartItem CART_새로운_아이템_요청 = CartRequest.AddCartItem.builder()
                .itemName(item.getEnName())
                .optionGroups(List.of(
                    CartRequest.AddCartItemOptionGroup.builder()
                        .name("사이즈")
                        .option(new CartRequest.AddCartItemOption("S", 500))
                        .build(),
                    CartRequest.AddCartItemOptionGroup.builder()
                        .name("색깔")
                        .option(new CartRequest.AddCartItemOption("파랑", 500))
                        .build()
                ))
                .amount(4)
                .build();
            cartService.addCartItem(user.getId(), CART_새로운_아이템_요청);

            //then
            Cart cart = cartRepository.get(user);
            assertThat(cart.getCartItems()).hasSize(2);
        }

        @Test
        @DisplayName("성공: 같은 아이템이지만 옵션값의 개수가 다를 경우 장바구니에 추가하면 새롭게 추가된다")
        void success3() {
            //given
            cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            //같은 아이템이지만 옵션 개수가 다르다
            CartRequest.AddCartItem CART_새로운_아이템_요청 = CartRequest.AddCartItem.builder()
                .itemName(item.getEnName())
                .optionGroups(List.of(
                    CartRequest.AddCartItemOptionGroup.builder()
                        .name("색깔")
                        .option(new CartRequest.AddCartItemOption("파랑", 500))
                        .build(),
                    CartRequest.AddCartItemOptionGroup.builder()
                        .name("사이즈")
                        .option(new CartRequest.AddCartItemOption("M", 1000))
                        .build(),
                    CartRequest.AddCartItemOptionGroup.builder()
                        .name("무늬")
                        .option(new CartRequest.AddCartItemOption("체크", 1000))
                        .build()
                ))
                .amount(4)
                .build();
            cartService.addCartItem(user.getId(), CART_새로운_아이템_요청);

            //then
            Cart cart = cartRepository.get(user);
            assertThat(cart.getCartItems()).hasSize(2);
        }

        @Test
        @DisplayName("성공: 같은 아이템이고 옵션값도 같은데 옵션값의 순서가 다를 경우 장바구니에 추가하면 새롭게 추가된다")
        void success4() {
            //given
            cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            CartRequest.AddCartItem CART_새로운_아이템_요청 = CartRequest.AddCartItem.builder()
                .itemName(item.getEnName())
                .optionGroups(List.of(
                    CartRequest.AddCartItemOptionGroup.builder()
                        .name("색깔")
                        .option(new CartRequest.AddCartItemOption("파랑", 500))
                        .build(),
                    CartRequest.AddCartItemOptionGroup.builder()
                        .name("사이즈")
                        .option(new CartRequest.AddCartItemOption("M", 1000))
                        .build()
                ))
                .amount(4)
                .build();
            cartService.addCartItem(user.getId(), CART_새로운_아이템_요청);

            //then
            Cart cart = cartRepository.get(user);
            assertThat(cart.getCartItems()).hasSize(2);
        }

        @Test
        @DisplayName("성공: 이미 장바구니에 있는 아이템을 같은 옵션으로 장바구니에 추가하면 수량만 증가한다")
        void success5() {
            //given
            var cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            cartService.addCartItem(user.getId(), CART_아이템_요청);

            //then
            var cart = cartRepository.get(user);
            var cartItem = cartItemRepository.get(cartItemId);

            assertThat(cart.getCartItems()).hasSize(1);
            assertThat(cartItem.getAmount()).isEqualTo(4);
        }
    }

    @Nested
    @DisplayName("장바구니 아이템 리스트를 조회한다")
    class getCartItems {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            var actual = cartService.getCartItems(user.getId());

            //then
            assertThat(actual).hasSize(1);
        }

        @Test
        @DisplayName("성공: 아무 아이템도 장바구니에 추가하지 않아 장바구니가 없는 상태에서 전체조회를 하면 장바구니가 생성된다")
        void success2() {
            //given

            //when
            var actual = cartService.getCartItems(user.getId());

            assertThat(actual).hasSize(0);
        }
    }

    @Nested
    @DisplayName("선택된 장바구니아이템 리스트를 조회한다")
    class getSelected {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            var cartItem = cartItemRepository.get(cartItemId);
            cartItem.select();
            var actual = cartService.getSelected(user.getId());

            //then
            assertAll(() -> {
                assertThat(actual.totalPrice()).isEqualTo(9000);
                assertThat(actual.selectedCartItems().get(0).name()).isEqualTo("No.1, 사이즈, M, 색깔, 파랑");
                assertThat(actual.selectedCartItems().get(0).amount()).isEqualTo(2);
            });
        }
    }

    @Nested
    @DisplayName("장바구니 아이템의 수량을 변경한다")
    class updateCartItemAmount {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            CartRequest.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartRequest.UpdateCartItemAmount.builder()
                .cartItemId(cartItemId)
                .amount(3)
                .build();
            cartService.updateCartItemAmount(user.getId(), CART_아이템_수량_변경_요청);

            //then
            var cartItem = cartItemRepository.get(cartItemId);
            assertThat(cartItem.getAmount()).isEqualTo(3);
        }

        @Test
        @DisplayName("실패: 존재하지 않는 장바구니아이템")
        void fail() {
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
    }

    @Nested
    @DisplayName("여러 장바구니아이템을 한번에 선택한다")
    class select {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            var request = new CartRequest.SelectCartItem(List.of(cartItemId));
            cartService.select(user.getId(), request);

            //then
            var actual = cartService.getSelected(user.getId());
            assertThat(actual.selectedCartItems()).hasSize(1);
        }
    }

    @Nested
    @DisplayName("장바구니 아이템을 삭제한다")
    class deleteCartItem {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when
            cartService.deleteCartItem(user.getId(), cartItemId);

            //then
            boolean hasCartItem = jpaCartItemRepository.findById(cartItemId).isPresent();
            assertThat(hasCartItem).isFalse();
        }

        @Test
        @DisplayName("실패: 존재하지 않는 장바구니아이템")
        void fail() {
            //given
            cartService.addCartItem(user.getId(), CART_아이템_요청);

            //when, then
            UUID invalidCartItemId = UUID.randomUUID();

            assertThatThrownBy(() -> cartService.deleteCartItem(user.getId(), invalidCartItemId))
                .isInstanceOf(CartItemNotFoundException.class);
        }
    }
}