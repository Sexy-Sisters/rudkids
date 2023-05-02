package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.common.fixtures.order.OrderServiceFixtures;
import com.rudkids.rudkids.domain.cart.domain.CartStatus;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import com.rudkids.rudkids.domain.order.exception.OrderNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderServiceTest extends OrderServiceFixtures {

    @DisplayName("[주문-주문요청]")
    @Test
    void 주문요청을_한다() {
        // given, when
        var orderId = orderService.create(ORDER_주문_요청(), user.getId());

        // then
        var findOrder = orderReader.getOrder(orderId);

        assertAll(
            () -> assertThat(findOrder.getPayMethod()).isEqualTo(PayMethod.TOSS),
            () -> assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.INIT)
        );
    }

    @DisplayName("주문-상태변경")
    @Test
    void 주문_상태를_변경한다() {
        // Given
        var status = OrderStatus.DELIVERY_COMPLETE;
        var orderId = order.getId();

        user.changeAuthorityAdmin();

        // When
        orderService.changeStatus(status, orderId);

        // Then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.DELIVERY_COMPLETE);
    }

    @DisplayName("[주문-취소]")
    @Test
    void 주문을_취소한다() {
        // Given
        var orderId = order.getId();
        var cart = order.getCart();

        // When
        orderService.delete(orderId);

        // Then

        assertAll(
            () -> assertThat(cart.getCartStatus()).isEqualTo(CartStatus.ACTIVE),
            () -> assertThatThrownBy(() -> orderReader.getOrder(orderId)).isInstanceOf(OrderNotFoundException.class)
        );
    }
}