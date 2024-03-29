package com.rudkids.core.order.service;

import com.rudkids.core.common.fixtures.order.OrderServiceFixtures;
import com.rudkids.core.order.domain.OrderStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderServiceTest extends OrderServiceFixtures {

    @Disabled("보류")
    @DisplayName("[주문-주문요청]")
    @Test
    void 주문요청을_한다() {
        // given, when
        var response = orderService.order(user.getId(), ORDER_주문_요청);

        // then
        var findOrder = orderRepository.get(response.orderId());

        assertThat(findOrder.getPaymentMethod()).isEqualTo("TOSS");
    }

    @Disabled("보류")
    @DisplayName("[주문-상세조회]")
    @Test
    void 주문을_상세조회한다() {
        // Given
        var orderId = order.getId();

        // When
        var info = orderService.get(orderId);

        // Then
        assertAll(() -> {
            assertThat(info.orderId()).isEqualTo(orderId);
            assertThat(info.paymentMethod()).isEqualTo("TOSS");
            assertThat(info.orderStatus()).isEqualTo(OrderStatus.ORDER);
            assertThat(info.delivery().receiverName()).isEqualTo("이규진");
            assertThat(info.delivery().receiverPhone()).isEqualTo("01029401509");
            assertThat(info.delivery().receivedAddress()).isEqualTo("(494999) 부산시 사하구 윤공단로123 나는 몰라용~");
            assertThat(info.delivery().message()).isEqualTo("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.");
        });
    }

    @Disabled("보류")
    @DisplayName("[주문-주문내역 조회")
    @Test
    void 주문내역을_조회한다() {
        // Given
        var userId = user.getId();
        orderService.order(userId, ORDER_주문_요청);

        // When
        var infoList = orderService.getAll(userId);

        // Then
        assertThat(infoList).hasSize(2);
    }

//    @DisplayName("[주문-취소]")
//    @Test
//    void 주문을_취소한다() {
//        // Given
//        var orderId = order.getId();
//        var cart = order.getCart();
//
//        // When
//        orderService.delete(orderId);
//
//        // Then
//        assertAll(
//            () -> assertThat(cart.getCartStatus()).isEqualTo(CartStatus.ACTIVE),
//            () -> assertThatThrownBy(() -> orderRepository.get(orderId))
//                .isInstanceOf(OrderNotFoundException.class)
//        );
//    }
}