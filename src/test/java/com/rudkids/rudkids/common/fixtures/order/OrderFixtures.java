package com.rudkids.rudkids.common.fixtures.order;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.order.OrderCommand;
import com.rudkids.rudkids.domain.order.OrderReader;
import com.rudkids.rudkids.domain.order.OrderStore;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import com.rudkids.rudkids.domain.order.service.OrderService;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class OrderFixtures {
    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderStore orderStore;

    @Autowired
    protected OrderReader orderReader;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemStore itemStore;

    protected static User user;
    private static Item item;

    protected static OrderCommand.Register ORDER_주문_요청() {
        return OrderCommand.Register.builder()
            .receiverName("이규진")
            .receiverPhone("010-5476-5574")
            .receiverAddress1("부산시 사하구 윤공단로123")
            .receiverAddress2("나는 몰라용~")
            .receiverZipcode("494999")
            .etcMessage("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.")
            .payMethod(PayMethod.TOSS)
            .build();
    }

    @BeforeEach
    void inputData() {
        user = User.builder()
            .name("이규진")
            .age(19)
            .email("leekuin14@gmail.com")
            .gender("MALE")
            .phoneNumber("01029401509")
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
    }
}
