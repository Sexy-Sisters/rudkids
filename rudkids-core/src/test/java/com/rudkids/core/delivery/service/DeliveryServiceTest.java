package com.rudkids.core.delivery.service;

import com.rudkids.core.common.fixtures.delivery.DeliveryServiceFixtures;
import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.delivery.dto.DeliveryRequest;
import com.rudkids.core.delivery.exception.DeliveryNotFoundException;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DeliveryServiceTest extends DeliveryServiceFixtures {

    @Nested
    @DisplayName("배송지를 등록한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            UUID deliveryId = deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //then
            var delivery = deliveryRepository.get(deliveryId);
            assertAll(() -> {
                assertThat(delivery.getReceiverName()).isEqualTo("수신자");
                assertThat(delivery.getReceiverPhone()).isEqualTo("01012345678");
                assertThat(delivery.getZipCode()).isEqualTo("324352");
                assertThat(delivery.getAddress()).isEqualTo("부산광역시");
                assertThat(delivery.getExtraAddress()).isEqualTo("서구");
                assertThat(delivery.getMessage()).isEqualTo("메세지");
                assertThat(delivery.isBasic()).isTrue();
            });
        }

        @Test
        @DisplayName("새로운 배송지를 기본으로 등록하면 기존의 기본배송지는 false로 변경된다")
        void success2() {
            //given
            deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when
            var newRequest = new DeliveryRequest.Create(
                "새로운 수신자",
                "01012345678",
                "324352",
                "부산광역시",
                "서구",
                "메세지",
                true
            );
            deliveryService.create(user.getId(), newRequest);

            //then
            Delivery actual = user.getDeliveries().stream()
                .filter(Delivery::isBasic)
                .findFirst()
                .orElseThrow();
            assertAll(() -> {
                assertThat(actual.isBasic()).isTrue();
                assertThat(actual.getReceiverName()).isEqualTo("새로운 수신자");
            });
        }

    }

    @Nested
    @DisplayName("배송지 리스트를 조회한다")
    class getAll {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when
            var actual = deliveryService.getAll(user.getId());

            //then
            assertThat(actual).hasSize(1);
        }
    }

    @Nested
    @DisplayName("배송지를 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID deliveryId = deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when
            var actual = deliveryService.get(user.getId(), deliveryId);

            //then
            assertAll(() -> {
                assertThat(actual.receiverName()).isEqualTo("수신자");
                assertThat(actual.receiverPhone()).isEqualTo("01012345678");
                assertThat(actual.zipCode()).isEqualTo("324352");
                assertThat(actual.address()).isEqualTo("부산광역시");
                assertThat(actual.extraAddress()).isEqualTo("서구");
                assertThat(actual.message()).isEqualTo("메세지");
                assertThat(actual.isBasic()).isTrue();
            });
        }

        @Test
        @DisplayName("실패: 존재하지 않는 배송지")
        void fail() {
            //given
            final UUID invalidDeliveryId = UUID.randomUUID();

            //when, then
            assertThatThrownBy(() -> deliveryService.get(user.getId(), invalidDeliveryId))
                .isInstanceOf(DeliveryNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("배송지를 기본배송지로 변경한다")
    class changeBasicStatus {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID deliveryId = deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when
            deliveryService.changeStatus(user.getId(), deliveryId);

            //then
            var actual = deliveryRepository.get(deliveryId);
            assertThat(actual.isBasic()).isTrue();
        }

        @Test
        @DisplayName("실패: 존재하지 않는 배송지")
        void fail() {
            //given
            final UUID invalidDeliveryId = UUID.randomUUID();

            //when, then
            assertThatThrownBy(() -> deliveryService.changeStatus(user.getId(), invalidDeliveryId))
                .isInstanceOf(DeliveryNotFoundException.class);
        }

        @Test
        @DisplayName("실패: 다른유저")
        void fail2() {
            //given
            UUID deliveryId = deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when, then
            ProfileImage profileImage = ProfileImage.create("path", "url");
            var anotherUser = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(profileImage)
                .build();
            userRepository.save(anotherUser);


            assertThatThrownBy(() -> deliveryService.changeStatus(anotherUser.getId(), deliveryId))
                .isInstanceOf(DifferentUserException.class);
        }
    }

    @Nested
    @DisplayName("배송지를 수정한다")
    class update {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID deliveryId = deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when
            var request = new DeliveryRequest.Update(
                "새로운 수신자",
                "01012345678",
                "0012312",
                "인천",
                "송도",
                "새로운 메세지"
            );
            deliveryService.update(user.getId(), deliveryId, request);

            //then
            var delivery = deliveryRepository.get(deliveryId);
            assertAll(() -> {
                assertThat(delivery.getReceiverName()).isEqualTo("새로운 수신자");
                assertThat(delivery.getReceiverPhone()).isEqualTo("01012345678");
                assertThat(delivery.getZipCode()).isEqualTo("0012312");
                assertThat(delivery.getAddress()).isEqualTo("인천");
                assertThat(delivery.getExtraAddress()).isEqualTo("송도");
                assertThat(delivery.getMessage()).isEqualTo("새로운 메세지");
                assertThat(delivery.isBasic()).isTrue();
            });
        }

        @Test
        @DisplayName("실패: 존재하지 않는 배송지")
        void fail() {
            //given
            final UUID invalidDeliveryId = UUID.randomUUID();

            //when, then
            var request = new DeliveryRequest.Update(
                "새로운 수신자",
                "01012345678",
                "0012312",
                "인천",
                "송도",
                "새로운 메세지"
            );
            assertThatThrownBy(() -> deliveryService.update(user.getId(), invalidDeliveryId, request))
                .isInstanceOf(DeliveryNotFoundException.class);
        }

        @Test
        @DisplayName("실패: 다른유저")
        void fail2() {
            //given
            UUID deliveryId = deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when, then
            ProfileImage profileImage = ProfileImage.create("path", "url");
            var anotherUser = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(profileImage)
                .build();
            userRepository.save(anotherUser);

            var request = new DeliveryRequest.Update(
                "새로운 수신자",
                "01012345678",
                "0012312",
                "인천",
                "송도",
                "새로운 메세지"
            );

            assertThatThrownBy(() -> deliveryService.update(anotherUser.getId(), deliveryId, request))
                .isInstanceOf(DifferentUserException.class);
        }
    }

    @Nested
    @DisplayName("배송지를 삭제한다")
    class delete {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID deliveryId = deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when
            deliveryService.delete(user.getId(), deliveryId);

            //then
            boolean actual = jpaDeliveryRepository.findById(deliveryId).isPresent();
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("실패: 존재하지 않는 배송지")
        void fail() {
            //given
            final UUID invalidDeliveryId = UUID.randomUUID();

            //when, then
            assertThatThrownBy(() -> deliveryService.delete(user.getId(), invalidDeliveryId))
                .isInstanceOf(DeliveryNotFoundException.class);
        }

        @Test
        @DisplayName("실패: 다른유저")
        void fail2() {
            //given
            UUID deliveryId = deliveryService.create(user.getId(), DELIVERY_등록_요청);

            //when, then
            ProfileImage profileImage = ProfileImage.create("path", "url");
            var anotherUser = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(profileImage)
                .build();
            userRepository.save(anotherUser);

            assertThatThrownBy(() -> deliveryService.delete(anotherUser.getId(), deliveryId))
                .isInstanceOf(DifferentUserException.class);
        }
    }
}
