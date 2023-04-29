package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.common.fixtures.order.OrderServiceFixtures;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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

}