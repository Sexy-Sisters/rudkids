package com.rudkids.core.common.fixtures;

import com.rudkids.core.cart.domain.Cart;
import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.service.CartService;
import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.delivery.domain.Address;
import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.delivery.domain.DeliveryRepository;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.order.domain.PayMethod;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.service.OrderService;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class OrderServiceFixtures {
    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected CartRepository cartRepository;

    @Autowired
    protected CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected JpaUserRepository jpaUserRepository;

    @Autowired
    protected ItemRepository itemRepository;

    @Autowired
    protected DeliveryRepository deliveryRepository;

    protected static Order order;
    protected static User user;
    protected static Cart cart;
    protected static Delivery delivery;

    protected static Item item;

    protected static OrderRequest.Create ORDER_주문_요청;

    protected static CartRequest.AddCartItem CART_아이템_요청() {
        return CartRequest.AddCartItem.builder()
            .itemId(item.getId())
            .optionGroups(List.of(
                CartRequest.AddCartItemOptionGroup.builder()
                    .name("사이즈")
                    .option(new CartRequest.AddCartItemOption("M", 1000))
                    .build(),
                CartRequest.AddCartItemOptionGroup.builder()
                    .name("색깔")
                    .option(new CartRequest.AddCartItemOption("파랑", 500))
                    .build()
            ))
            .amount(2)
            .build();
    }

    @BeforeEach
    void inputData() {
        delivery = Delivery.builder()
            .receiverName("이규진")
            .receiverPhone("01029401509")
            .address(Address.create("부산시 사하구 윤공단로123", "나는 몰라용~", "494999"))
            .message("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.")
            .build();
        deliveryRepository.save(delivery);

        ProfileImage profileImage = ProfileImage.create("path", "url");
        user = User.builder()
            .name(UserName.create("이규진"))
            .age(19)
            .email("leekuin14@gmail.com")
            .gender("MAIL")
            .phoneNumber(PhoneNumber.create("01029401509"))
            .profileImage(profileImage)
            .socialType(SocialType.GOOGLE)
            .build();
        jpaUserRepository.save(user);

        item = Item.builder()
            .name(Name.create("No.1", "남바완"))
            .price(Price.create(3_000))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.LIMITED)
            .build();

        ItemImage image = ItemImage.create(item, "path", "url");
        item.addImage(image);
        itemRepository.save(item);

        cartService.addCartItem(user.getId(), CART_아이템_요청());

        cart = cartRepository.getActiveCart(user);

        order = Order.create(user, cart, delivery, PayMethod.TOSS);
        orderRepository.save(order);

        ORDER_주문_요청 = new OrderRequest.Create(PayMethod.TOSS, delivery.getId());
    }
}
