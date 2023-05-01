package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.common.fixtures.order.OrderServiceFixtures;
import com.rudkids.rudkids.domain.cart.exception.CartNotFoundException;
import com.rudkids.rudkids.domain.order.exception.OrderNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderServiceFailTest extends OrderServiceFixtures {

    @DisplayName("[주문-생성-CartNotFoundException]")
    @Test
    void 활성화된_장바구니가_없을_때_주문_생성_시_예외가_발생한다() {
        // Given
        var userId = user.getId();
        cart.deactivate();

        // When & Then
        assertThatThrownBy(() -> orderService.create(ORDER_주문_요청(), userId))
            .isInstanceOf(CartNotFoundException.class);
    }

    @DisplayName("[주문-취소-OrderNotFoundException]")
    @Test
    void 존재하지_않는_주문_취소_시_예외가_발생한다() {
        // Given
        var orderId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> orderService.delete(orderId))
            .isInstanceOf(OrderNotFoundException.class);
    }
}
