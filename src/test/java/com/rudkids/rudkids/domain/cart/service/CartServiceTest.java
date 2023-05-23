package com.rudkids.rudkids.domain.cart.service;

import  com.rudkids.rudkids.common.fixtures.cart.CartServiceFixtures;
import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartInfo;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItemOptionGroup;
import com.rudkids.rudkids.domain.item.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CartServiceTest extends CartServiceFixtures {

    @DisplayName("[장바구니-생성]")
    @Test
    void 회원의_장바구니는_존재하지_않다가_처음_아이템을_추가할_때_장바구니를_생성한다() {
        //given
        List<Cart> carts = cartRepository.findAll();

        //when
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        assertThat(carts).isEmpty();
        cartReader.getActiveCart(user);
    }

    @DisplayName("[장바구니-아이템추가]")
    @Test
    void 장바구니에_아이템을_추가한다() {
        //given, when
        var cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        Cart cart = cartReader.getActiveCart(user);
        var cartItem = cartItemReader.getCartItem(cartItemId);

        assertAll(() -> {
            assertThat(cart.getCartItems()).hasSize(1);
            assertThat(cartItem.getAmount()).isEqualTo(2);
        });
    }

    @DisplayName("[장바구니-아이템추가-옵션]")
    @Test
    void 옵션이_붙은_아이템을_장바구니에_추가할_경우_옵션가격이_추가된다() {
        //given, when
        UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        var cartItem = cartItemReader.getCartItem(cartItemId);

        assertThat(cartItem.calculateTotalItemPrice()).isEqualTo(9_000);
    }

    @DisplayName("[장바구니-새로운아이템추가]")
    @Test
    void 장바구니에_새로운_아이템을_추가한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);
        Item newItem = Item.builder()
            .name(Name.create("No.2"))
            .price(Price.create(6_990))
            .quantity(Quantity.create(3_000))
            .itemBio(ItemBio.create("옷 팝니다!"))
            .limitType(LimitType.LIMITED)
            .build();

        ItemImage image = ItemImage.create(newItem, "path", "url");
        newItem.addImage(image);
        itemRepository.save(newItem);

        //when
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
        Cart cart = cartReader.getActiveCart(user);

        assertThat(cart.getCartItems()).hasSize(2);
    }

    @DisplayName("[장바구니-아이템추가-다른옵션]")
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
        Cart cart = cartReader.getActiveCart(user);

        assertThat(cart.getCartItems()).hasSize(2);
    }

    @DisplayName("[장바구니-아이템추가-다른옵션개수]")
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
        Cart cart = cartReader.getActiveCart(user);

        assertThat(cart.getCartItems()).hasSize(2);
    }

    @DisplayName("[장바구니-아이템수량증가-같은옵션]")
    @Test
    void 같은_아이템이고_옵션_값은_같지만_순서가_다를_경우_장바구니아이템을_수량만_증가한다() {
        //given
        var cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

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
        Cart cart = cartReader.getActiveCart(user);
        var cartItem = cartItemReader.getCartItem(cartItemId);

        assertThat(cart.getCartItems()).hasSize(1);
        assertThat(cartItem.getAmount()).isEqualTo(6);
    }

    @DisplayName("[장바구니-아이템수량증가]")
    @Test
    void 이미_장바구니에_있는_아이템을_추가하면_장바구니아이템_수량만_증가한다() {
        //given
        var cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //then
        var cart = cartReader.getActiveCart(user);
        var cartItem = cartItemReader.getCartItem(cartItemId);

        assertThat(cart.getCartItems()).hasSize(1);
        assertThat(cartItem.getAmount()).isEqualTo(4);
    }

    @DisplayName("[장바구니-아이템전체조회]")
    @Test
    void 장바구니에_담겨있는_아이템_리스트를_조회한다() {
        //given
        cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        CartInfo.Main actual = cartService.findCartItems(user.getId());

        //then
        assertAll(() -> {
            assertThat(actual.totalCartItemPrice()).isEqualTo(9_000);
            assertThat(actual.cartItems()).hasSize(1);
        });
    }

    @DisplayName("[장바구니-아이템수량변경]")
    @Test
    void 장바구니_아이템의_수량을_변경한다() {
        //given
        var cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);
        var cartItem = cartItemReader.getCartItem(cartItemId);

        //when
        CartCommand.UpdateCartItemAmount CART_아이템_수량_변경_요청 = CartCommand.UpdateCartItemAmount.builder()
            .cartItemId(cartItemId)
            .amount(3)
            .build();
        cartService.updateCartItemAmount(user.getId(), CART_아이템_수량_변경_요청);

        //then
        assertThat(cartItem.getAmount()).isEqualTo(3);
    }

    @DisplayName("[장바구니-아이템선택삭제]")
    @Test
    void 장바구니_아이템들을_선택하여_삭제한다() {
        //given
        UUID cartItemId = cartService.addCartItem(user.getId(), CART_아이템_요청);

        //when
        List<UUID> cartItemIds = List.of(cartItemId);

        CartCommand.DeleteCartItems CART_아이템_삭제_요청 = CartCommand.DeleteCartItems.builder()
            .cartItemIds(cartItemIds)
            .build();
        cartService.deleteCartItems(user.getId(), CART_아이템_삭제_요청);

        //then
        boolean hasCartItem = cartItemRepository.findById(cartItemId).isPresent();
        Cart actual = cartReader.getActiveCart(user);
        List<CartItemOptionGroup> hasCartItemOptionGroup = cartItemOptionGroupRepository.findAll();

        assertAll(() -> {
            assertThat(hasCartItem).isFalse();
            assertThat(actual.getCartItems()).hasSize(0);
            assertThat(hasCartItemOptionGroup).isEmpty();
        });
    }
}