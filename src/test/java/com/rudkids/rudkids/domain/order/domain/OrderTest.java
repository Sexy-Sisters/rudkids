package com.rudkids.rudkids.domain.order.domain;

import com.rudkids.rudkids.common.fixtures.order.OrderFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest extends OrderFixtures {

    @DisplayName("주문 총 가격 구하기")
    @Test
    void 주문_총_가격_구하기() {
        // Given
        UUID orderId = orderService.create(user.getId(), ORDER_주문_요청());
        Order findOrder = orderReader.getOrder(orderId);

        // When & Then
        assertThat(findOrder.calculateTotalAmount()).isEqualTo(17500);
    }
}