package com.rudkids.rudkids.domain.cart.domain;

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
}