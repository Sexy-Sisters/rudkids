package com.rudkids.rudkids.common.fixtures.cart;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.cart.application.CartCommand;
import com.rudkids.rudkids.domain.cart.application.CartService;
import com.rudkids.rudkids.domain.cart.repository.CartItemRepository;
import com.rudkids.rudkids.domain.cart.repository.CartRepository;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.Gender;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.repository.UserRepository;
import com.rudkids.rudkids.infrastructure.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class CartServiceFixtures {

    @Autowired
    protected CartService cartService;

    @Autowired
    protected CartRepository cartRepository;

    @Autowired
    protected CartItemRepository cartItemRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ItemRepository itemRepository;

    protected User user;
    protected Item item;

    protected CartCommand.AddCartItem CART_아이템_요청;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(user);

        item = Item.builder()
                .name(Name.create("No.1"))
                .price(Price.create(2_990))
                .quantity(Quantity.create(1_000))
                .itemBio(ItemBio.create("소개글입니다~"))
                .limitType(LimitType.LIMITED)
                .build();
        itemRepository.save(item);

        //저장된 itemId 넣음
        CART_아이템_요청 = CartCommand.AddCartItem.builder()
                .itemId(item.getId())
                .amount(2)
                .build();
    }
}
