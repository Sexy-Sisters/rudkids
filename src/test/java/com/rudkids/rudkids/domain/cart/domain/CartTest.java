package com.rudkids.rudkids.domain.cart.domain;

import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.Gender;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    @DisplayName("추가된 아이템의 개수만큼 장바구니아이템 수량이 증가한다.")
    @Test
    void 추가된_아이템의_개수만큼_장바구니아이템_수량이_증가한다() {
        //given
        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        Cart cart = Cart.create(user);

        //when
        final int ITEM_AMOUNT = 3;
        cart.addCartItemCount(ITEM_AMOUNT);

        //then
        assertThat(cart.getCartItemCount()).isEqualTo(3);
    }

    @DisplayName("카트에 담긴 아이템들의 총 가격을 반환한다.")
    @Test
    void 카트에_담긴_아이테믈의_총_가격을_반환한다() {
        //given
        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        Cart cart = Cart.create(user);

        //when
        Item item = Item.builder()
                .name(Name.create("No.1"))
                .price(Price.create(2_000))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();

        Item item2 = Item.builder()
                .name(Name.create("No.1"))
                .price(Price.create(3_000))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();

        CartItem cartItem = CartItem.create(cart, item, 2);
        CartItem cartItem2 = CartItem.create(cart, item2, 3);
        cart.addCartItem(cartItem);
        cart.addCartItem(cartItem2);

        //then
        assertThat(cart.getTotalCartItemPrice()).isEqualTo(13000);
    }
}