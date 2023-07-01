package com.rudkids.core.cart.domain;

import com.rudkids.core.item.domain.*;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CartTest {

    @DisplayName("추가된 아이템의 개수만큼 장바구니아이템 수량이 증가한다.")
    @Test
    void 추가된_아이템의_개수만큼_장바구니아이템_수량이_증가한다() {
        //given
        User user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        Cart cart = Cart.create(user);

        //when
        final int ITEM_AMOUNT = 3;


        //then
    }

    @DisplayName("카트에 담긴 아이템들의 총 가격을 반환한다.")
    @Test
    void 카트에_담긴_아이테믈의_총_가격을_반환한다() {
        //given
        User user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        Cart cart = Cart.create(user);

        //when
        Item item = Item.builder()
                .name(Name.create("No.1", "남바완"))
                .price(Price.create(2_000))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();

        Item item2 = Item.builder()
                .name(Name.create("No.1", "남바완"))
                .price(Price.create(3_000))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .amount(2)
                .price(item.getPrice())
                .build();

        CartItem cartItem2 = CartItem.builder()
                .cart(cart)
                .item(item)
                .amount(3)
                .price(item2.getPrice())
                .build();
        cart.addCartItem(cartItem);
        cart.addCartItem(cartItem2);

        //then
        assertThat(cart.calculateTotalPrice()).isEqualTo(13000);
    }

    @DisplayName("아이템의 수량을 변경하면 장바구니 아이템 수량도 변경한다.")
    @Test
    void 아이템의_수량을_변경하면_장바구니_아이템_수량도_변경한다() {
        //given
        User user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        Cart cart = Cart.create(user);

        //when
        final int cartItemAmount = 2;

        //then
    }

    @DisplayName("같은 유저일 경우 통과한다.")
    @Test
    void 같은_유저일_경우_통과한다() {
        //given, when
        User user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        Cart cart = Cart.create(user);

        //then
        cart.validateHasSameUser(user);
    }

    @DisplayName("다른 유저일 경우 예외가 발생한다.")
    @Test
    void 다른_유저일_경우_예외가_발생한다() {
        //given
        User user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        Cart cart = Cart.create(user);

        //then
        User differentUser = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("다른유저"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();

        //then
        assertThatThrownBy(() -> cart.validateHasSameUser(differentUser))
                .isInstanceOf(DifferentUserException.class);
    }
}