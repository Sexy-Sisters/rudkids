package com.rudkids.rudkids.common.fixtures.cart;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.service.CartService;
import com.rudkids.rudkids.infrastructure.cart.CartItemOptionGroupRepository;
import com.rudkids.rudkids.infrastructure.cart.CartItemRepository;
import com.rudkids.rudkids.infrastructure.cart.CartRepository;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import com.rudkids.rudkids.infrastructure.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Autowired
    protected CartItemOptionGroupRepository cartItemOptionGroupRepository;

    protected User user;
    protected Item item;

    protected CartCommand.AddCartItem CART_아이템_요청;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .phoneNumber("01029401509")
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
                .optionGroups(List.of(
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("사이즈")
                                .option(new CartCommand.AddCartItemOption("M", 1000))
                                .build(),
                        CartCommand.AddCartItemOptionGroup.builder()
                                .name("색깔")
                                .option(new CartCommand.AddCartItemOption("파랑", 500))
                                .build()
                        ))
                .amount(2)
                .build();
    }
}
