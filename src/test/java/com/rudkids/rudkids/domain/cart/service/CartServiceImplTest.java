package com.rudkids.rudkids.domain.cart.service;

import com.rudkids.rudkids.common.fixtures.cart.CartServiceFixtures;
import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartInfo;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.domain.CartItemOptionGroup;
import com.rudkids.rudkids.domain.cart.exception.CartItemNotFoundException;
import com.rudkids.rudkids.domain.cart.exception.CartNotFoundException;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
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

    @DisplayName("옵션이 붙은 아이템을 장바구니에 추가할 경우 옵션가격이 추가된다.")
    @Test
    void 옵션이_붙은_아이템을_장바구니에_추가할_경우_옵션가격이_추가된다() {
        //given, when
        UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        CartItem actual = cartItemRepository.findById(cartItemId)
                        .orElseThrow(CartItemNotFoundException::new);

        assertThat(actual.getCartItemPrice()).isEqualTo(8980);
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
                .optionGroups(List.of(
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("사이즈")
                                .option(new CartCommand.AddCartItemOption("M", 1000))
                                .build()))
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

    @DisplayName("같은 아이템이지만 옵션값이 다를 경우 장바구니아이템을 새롭게 추가한다.")
    @Test
    void 같은_아이템이지만_옵션값이_다를_경우_장바구니에_추가하면_새롭게_저장된다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        CartCommand.AddCartItem CART_새로운_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .optionGroups(List.of(
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("사이즈")
                                .option(new CartCommand.AddCartItemOption("S", 500))
                                .build(),
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("색깔")
                                .option(new CartCommand.AddCartItemOption("파랑", 500))
                                .build()
                ))
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

    @DisplayName("같은 아이템이지만 옵션 개수가 다를 경우 장바구니아이템을 새롭게 추가한다.")
    @Test
    void 같은_아이템이지만_옵션_개수가_다를_경우_장바구니아이템을_새롭게_추가한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        CartCommand.AddCartItem CART_새로운_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .optionGroups(List.of(
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("색깔")
                                .option(new CartCommand.AddCartItemOption("파랑", 500))
                                .build(),
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("사이즈")
                                .option(new CartCommand.AddCartItemOption("M", 1000))
                                .build(),
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("무늬")
                                .option(new CartCommand.AddCartItemOption("체크", 1000))
                                .build()
                ))
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

    @DisplayName("같은 아이템이고 옵션 값은 같지만 순서가 다를 경우 장바구니아이템 수량만 증가한다.")
    @Test
    void 같은_아이템이고_옵션_값은_같지만_순서가_다를_경우_장바구니아이템을_수량만_증가한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        CartCommand.AddCartItem CART_새로운_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .optionGroups(List.of(
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("색깔")
                                .option(new CartCommand.AddCartItemOption("파랑", 500))
                                .build(),
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("사이즈")
                                .option(new CartCommand.AddCartItemOption("M", 1000))
                                .build()
                ))
                .amount(4)
                .build();
        cartService.addCartItem(user.getId(), CART_새로운_아이템_요청);

        //then
        Cart actual = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getCartItemCount()).isEqualTo(6);
            assertThat(actual.getCartItems()).hasSize(1);
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

        //when
        CartInfo.Main actual = cartService.findCartItems(user.getId());

        //then
        assertAll(() -> {
            assertThat(actual.totalCartItemPrice()).isEqualTo(8980);
            assertThat(actual.cartItems()).hasSize(1);
        });
    }

    @DisplayName("장바구니 아이템의 수량을 변경한다.")
    @Test
    void 장바구니_아이템의_수량을_변경한다() {
        //given
        UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        //when
        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartId(cart.getId())
                .cartItemId(cartItemId)
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
        UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        //when
        User anotherUser = User.builder()
                .email("another@gmail.com")
                .name("다른사용자")
                .age(18)
                .gender("MALE")
                .phoneNumber("01029401509")
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(anotherUser);

        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartId(cart.getId())
                .cartItemId(cartItemId)
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
        UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        //when
        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
                .cartId(cart.getId())
                .cartItemId(cartItemId)
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
        UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        //when
        List<UUID> cartItemIds = List.of(cartItemId);

        CartCommand.DeleteCartItems CART_아이템_삭제_요청 = CartCommand.DeleteCartItems.builder()
                .cartId(cart.getId())
                .cartItemIds(cartItemIds)
                .build();
        cartService.deleteCartItems(user.getId(), CART_아이템_삭제_요청);

        //then
        boolean hasCartItem = cartItemRepository.findById(cartItemId).isPresent();
        Cart actual = cartRepository.findByUserId(user.getId())
                        .orElseThrow(CartNotFoundException::new);
        List<CartItemOptionGroup> hasCartItemOptionGroup = cartItemOptionGroupRepository.findAll();

        assertAll(() -> {
            assertThat(hasCartItem).isFalse();
            assertThat(actual.getCartItems()).hasSize(0);
            assertThat(hasCartItemOptionGroup).isEmpty();
        });
    }

    @Disabled("벌크연산 말고 다른 방법을 사용하여 커스텀 예외를 구현할 때까지 이 테스트코드는 보류한다.")
    @DisplayName("존재하지 않는 장바구니 아이템들을 선택하여 삭제할 경우 예외가 발생한다.")
    @Test
    void 존재하지_않는장바구니_아이템들을_선택하여_삭제할_경우_예외가_발생한다() {
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
        UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(CartNotFoundException::new);

        //when
        User anotherUser = User.builder()
                .email("another@gmail.com")
                .name("다른사용자")
                .age(18)
                .gender("MALE")
                .phoneNumber("01029401509")
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(anotherUser);

        //then
        List<UUID> cartItemIds = List.of(cartItemId);
        CartCommand.DeleteCartItems CART_아이템_삭제_요청 = CartCommand.DeleteCartItems.builder()
                .cartId(cart.getId())
                .cartItemIds(cartItemIds)
                .build();

        assertThatThrownBy(() -> cartService.deleteCartItems(anotherUser.getId(), CART_아이템_삭제_요청))
                .isInstanceOf(DifferentUserException.class);
    }
}