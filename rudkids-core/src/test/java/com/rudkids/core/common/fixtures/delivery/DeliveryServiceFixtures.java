package com.rudkids.core.common.fixtures.delivery;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.delivery.domain.DeliveryRepository;
import com.rudkids.core.delivery.dto.DeliveryRequest;
import com.rudkids.core.delivery.infrastructure.JpaDeliveryRepository;
import com.rudkids.core.delivery.service.DeliveryService;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class DeliveryServiceFixtures {

    @Autowired
    protected DeliveryService deliveryService;

    @Autowired
    protected DeliveryRepository deliveryRepository;

    @Autowired
    protected JpaDeliveryRepository jpaDeliveryRepository;

    @Autowired
    protected JpaUserRepository userRepository;

    protected User user;
    protected DeliveryRequest.Create DELIVERY_등록_요청 = new DeliveryRequest.Create(
        "수신자",
        "01012345678",
        "324352",
        "부산광역시",
        "서구",
        "메세지",
        true
    );

    @BeforeEach
    void setUp() {
        ProfileImage profileImage = ProfileImage.create("path", "url");
        user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(profileImage)
            .build();
        userRepository.save(user);
    }
}
