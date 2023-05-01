package com.rudkids.rudkids.common.fixtures.order;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartReader;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.service.CartService;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.order.OrderCommand;
import com.rudkids.rudkids.domain.order.OrderReader;
import com.rudkids.rudkids.domain.order.OrderStore;
import com.rudkids.rudkids.domain.order.domain.DeliveryFragment;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import com.rudkids.rudkids.domain.order.service.OrderService;
import com.rudkids.rudkids.domain.user.domain.PhoneNumber;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.domain.UserName;
import com.rudkids.rudkids.infrastructure.cart.CartRepository;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class OrderServiceFixtures {
    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderStore orderStore;

    @Autowired
    protected OrderReader orderReader;

    @Autowired
    protected CartReader cartReader;

    @Autowired
    protected CartRepository cartRepository;

    @Autowired
    protected CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemStore itemStore;

    protected static Order order;
    protected static User user;
    protected static Cart cart;
    protected static Item item;

    protected static DeliveryFragment deliveryFragment = DeliveryFragment.builder()
        .receiverName("이규진")
        .receiverPhone("010-5476-5574")
        .receiverAddress1("부산시 사하구 윤공단로123")
        .receiverAddress2("나는 몰라용~")
        .receiverZipcode("494999")
        .etcMessage("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.")
        .build();


    protected static OrderCommand.CreateRequest ORDER_주문_요청() {
        return OrderCommand.CreateRequest.builder()
            .receiverName("이규진")
            .receiverPhone("010-5476-5574")
            .receiverAddress1("부산시 사하구 윤공단로123")
            .receiverAddress2("나는 몰라용~")
            .receiverZipcode("494999")
            .etcMessage("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.")
            .payMethod(PayMethod.TOSS)
            .build();
    }

    protected static CartCommand.AddCartItem CART_아이템_요청() {
        return CartCommand.AddCartItem.builder()
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

    @BeforeEach
    void inputData() {
        user = User.builder()
            .name(UserName.create("이규진"))
            .age(19)
            .email("leekuin14@gmail.com")
            .gender("MAIL")
            .phoneNumber(PhoneNumber.create("010-2940-1509"))
            .socialType(SocialType.GOOGLE)
            .build();
        userRepository.save(user);

        item = Item.builder()
            .name(Name.create("No.1"))
            .price(Price.create(3_000))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.LIMITED)
            .build();
        itemStore.store(item);

        cartService.addCartItem(user.getId(), CART_아이템_요청());

        cart = cartReader.getActiveCart(user);

        order = Order.builder()
            .deliveryFragment(deliveryFragment)
            .payMethod(PayMethod.TOSS)
            .cart(cart)
            .build();

        orderStore.store(order);
    }
}
