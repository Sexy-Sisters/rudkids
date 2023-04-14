package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.common.fixtures.cart.CartServiceFixtures;
import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartInfo;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.exception.CartItemNotFoundException;
import com.rudkids.rudkids.domain.cart.exception.CartNotFoundException;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.Gender;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.DifferentUserException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @DisplayName("장바구니에 존재하지 않는 아이템을 추가할 경우 예외가 발생한다.")
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

    @DisplayName("장바구니 아이템의 수량을 변경한다.")
    @Test
    void 장바구니_아이템의_수량을_변경한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item)
                .orElseThrow(CartItemNotFoundException::new);

        //when
        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartId(cart.getId())
                .cartItemId(cartItem.getId())
                .amount(3)
                .build();
        cartService.updateCartItemAmount(user.getId(), CART_아이템_수량_변경_요청);

        //then
        CartItem findCartItem = cartItemRepository.findByCartAndItem(cart, item)
                        .orElseThrow(CartItemNotFoundException::new);

        Cart findCart = cartRepository.findByUserId(user.getId())
                        .orElseThrow(CartNotFoundException::new);
        CartItem actual = findCart.getCartItems().get(0);

        assertAll(() -> {
            assertThat(findCartItem.getAmount()).isEqualTo(3);
            assertThat(actual.getAmount()).isEqualTo(3);
        });
    }

    @DisplayName("다른 사용자의 장바구니 아이템의 수량을 변경할 시 예외가 발생한다.")
    @Test
    void 다른_사용자의_장바구니_아이템의_수량을_변경할_시_예외가_발생한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item)
                .orElseThrow(CartItemNotFoundException::new);

        //when
        User anotherUser = User.builder()
                .email("another@gmail.com")
                .name("다른사용자")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(anotherUser);

        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartId(cart.getId())
                .cartItemId(cartItem.getId())
                .amount(3)
                .build();

        //then
        assertThatThrownBy(() -> cartService.updateCartItemAmount(anotherUser.getId(), CART_아이템_수량_변경_요청))
                .isInstanceOf(DifferentUserException.class);
    }

    @DisplayName("존재하지 않는 장바구니 아이템의 수량을 변경할 시 예외가 발생한다.")
    @Test
    void 존재하지_않는_장바구니_아이템의_수량을_변경할_시_예외가_발생한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        UUID invalidCartItemId = UUID.randomUUID();
        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartId(cart.getId())
                .cartItemId(invalidCartItemId)
                .amount(3)
                .build();

        //then
        assertThatThrownBy(() -> cartService.updateCartItemAmount(user.getId(), CART_아이템_수량_변경_요청))
                .isInstanceOf(CartItemNotFoundException.class);
    }

    @DisplayName("장바구니 아이템의 수량을 변경하면 장바구니에 담겨있는 아이템의 총 수량도 변경된다")
    @Test
    void 장바구니_아이템의_수량을_변경하면_장바구니에_담겨있는_아이템의_총_수량도_변경된다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item)
                .orElseThrow(CartItemNotFoundException::new);

        //when
        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartId(cart.getId())
                .cartItemId(cartItem.getId())
                .amount(7)
                .build();
        cartService.updateCartItemAmount(user.getId(), CART_아이템_수량_변경_요청);

        //then
        Cart findCart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        assertThat(findCart.getCartItemCount()).isEqualTo(7);
    }

    @DisplayName("장바구니 아이템들을 선택하여 삭제한다.")
    @Test
    void 장바구니_아이템들을_선택하여_삭제한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item)
                .orElseThrow(CartItemNotFoundException::new);

        //when
        List<UUID> cartItemIds = List.of(cartItem.getId());

        CartCommand.DeleteCartItems CART_아이템_삭제_요청 = CartCommand.DeleteCartItems.builder()
                .cartId(cart.getId())
                .cartItemIds(cartItemIds)
                .build();
        cartService.deleteCartItems(user.getId(), CART_아이템_삭제_요청);

        //then
        boolean hasCartItem = cartItemRepository.findById(cartItem.getId()).isPresent();
        boolean hasAnotherCartItem = cartItemRepository.findById(cartItem.getId()).isPresent();

        Cart actual = cartRepository.findByUserId(user.getId())
                        .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(hasCartItem).isFalse();
            assertThat(hasAnotherCartItem).isFalse();
            assertThat(actual.getCartItems()).hasSize(0);
        });
    }

    @Disabled("벌크연산 말고 다른 방법을 사용하여 커스텀 예외를 구현할 때까지 보류")
    @DisplayName("없는 장바구니 아이템들을 선택하여 삭제할 경우 예외가 발생한다.")
    @Test
    void 장바구니_아이템들을_선택하여_삭제할_경우_예외가_발생한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        //when
        UUID invalidCartItemId = UUID.randomUUID();
        List<UUID> cartItemIds = List.of(invalidCartItemId);

        //then
        CartCommand.DeleteCartItems CART_아이템_삭제_요청 = CartCommand.DeleteCartItems.builder()
                .cartId(cart.getId())
                .cartItemIds(cartItemIds)
                .build();

        assertThatThrownBy(() -> cartService.deleteCartItems(user.getId(), CART_아이템_삭제_요청))
                .isInstanceOf(CartItemNotFoundException.class);
    }

    @DisplayName("다른 사용자의 장바구니 아이템들을 선택하여 삭제할 경우 예외가 발생한다.")
    @Test
    void 다른_사용자의_장바구니_아이템들을_선택하여_삭제할_경우_예외가_발생한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item)
                .orElseThrow(CartItemNotFoundException::new);

        //when
        User anotherUser = User.builder()
                .email("another@gmail.com")
                .name("다른사용자")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(anotherUser);

        //then
        List<UUID> cartItemIds = List.of(cartItem.getId());
        CartCommand.DeleteCartItems CART_아이템_삭제_요청 = CartCommand.DeleteCartItems.builder()
                .cartId(cart.getId())
                .cartItemIds(cartItemIds)
                .build();

        assertThatThrownBy(() -> cartService.deleteCartItems(anotherUser.getId(), CART_아이템_삭제_요청))
                .isInstanceOf(DifferentUserException.class);
    }
}