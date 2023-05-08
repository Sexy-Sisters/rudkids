package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.common.fixtures.order.OrderServiceFixtures;
import com.rudkids.rudkids.domain.cart.exception.CartNotFoundException;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import com.rudkids.rudkids.domain.order.exception.OrderNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @DisplayName("[주문-상세조회-OrderNotFoundException]")
    @Test
    void 존재하지_않는_주문을_상세조회_시_예외가_발생한다() {
        // Given
        var orderId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> orderService.find(orderId))
            .isInstanceOf(OrderNotFoundException.class);
    }

    @Disabled("@AuthenticationAdminAuthority 어떻게 테스트 하는지 알아보기")
    @DisplayName("주문-상태변경-NotAdminRoleException")
    @Test
    void 어드민이_아닌_유저가_주문_상태를_변경_시_예외가_발생한다() {
        // Given
        var status = OrderStatus.DELIVERY_COMPLETE;
        var orderId = order.getId();

        // When & Then
        user.changeAuthorityPartner();
        assertThatThrownBy(() -> orderService.changeStatus(status, orderId))
            .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("주문-상태변경-OrderNotFoundException")
    @Test
    void 존재하지_않는_주문의_상태를_변경_시_예외가_발생한다() {
        // Given
        var status = OrderStatus.ORDER_COMPLETE;
        var orderId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> orderService.changeStatus(status, orderId))
            .isInstanceOf(OrderNotFoundException.class);
    }

    @DisplayName("[주문-배송 정보 수정]")
    @Test
    void 배송정보를_수정한다() {
        // Given
        var orderId = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> orderService.updateDeliveryFragment(ORDER_배송_정보_수정_요청(), orderId))
            .isInstanceOf(OrderNotFoundException.class);
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
