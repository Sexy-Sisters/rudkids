package com.rudkids.core.order.service;

import com.rudkids.core.common.fixtures.order.OrderServiceFixtures;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.exception.OrderNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderServiceFailTest extends OrderServiceFixtures {

    @Disabled("보류")
    @DisplayName("[주문-상세조회-OrderNotFoundException]")
    @Test
    void 존재하지_않는_주문을_상세조회_시_예외가_발생한다() {
        // Given
        var orderId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> orderService.get(orderId))
            .isInstanceOf(OrderNotFoundException.class);
    }

    @Disabled("보류")
    @DisplayName("주문-상태변경-OrderNotFoundException")
    @Test
    void 존재하지_않는_주문의_상태를_변경_시_예외가_발생한다() {
        // Given
        OrderRequest.ChangeStatus statusRequest = new OrderRequest.ChangeStatus("ORDER_COMPLETE");
        var orderId = UUID.randomUUID();

        // When & Then
//        assertThatThrownBy(() -> orderService.changeStatus(orderId, statusRequest))
//            .isInstanceOf(OrderNotFoundException.class);
    }

    @Disabled("보류")
    @DisplayName("[주문-취소-OrderNotFoundException]")
    @Test
    void 존재하지_않는_주문_취소_시_예외가_발생한다() {
        // Given
        var orderId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> orderService.delete(user.getId(), orderId))
            .isInstanceOf(OrderNotFoundException.class);
    }
}
