package com.rudkids.core.order.service;

import com.rudkids.core.common.fixtures.OrderServiceFixtures;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.domain.PayMethod;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.exception.OrderNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderServiceTest extends OrderServiceFixtures {

    @Disabled("보류")
    @DisplayName("[주문-주문요청]")
    @Test
    void 주문요청을_한다() {
        // given, when
        var orderId = orderService.order(user.getId(), ORDER_주문_요청);

        // then
        var findOrder = orderRepository.get(orderId);

        assertAll(
            () -> assertThat(findOrder.getPayMethod()).isEqualTo(PayMethod.TOSS),
            () -> assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDER)
        );
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
            assertThat(info.payMethod()).isEqualTo(PayMethod.TOSS);
            assertThat(info.orderStatus()).isEqualTo(OrderStatus.ORDER);
            assertThat(info.deliveryFragment().receiverName()).isEqualTo("이규진");
            assertThat(info.deliveryFragment().receiverPhone()).isEqualTo("01029401509");
            assertThat(info.deliveryFragment().address()).isEqualTo("부산시 사하구 윤공단로123");
            assertThat(info.deliveryFragment().extraAddress()).isEqualTo("나는 몰라용~");
            assertThat(info.deliveryFragment().zipCode()).isEqualTo("494999");
            assertThat(info.deliveryFragment().message()).isEqualTo("나는 2024년 총 매출 35억을 달성했고 다낭으로 여행왔다. 나는 2024년 페라리를 샀다.");
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