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

    @DisplayName("[주문-상세조회]")
    @Test
    void 주문을_상세조회한다() {
        // Given
        var orderId = order.getId();

        // When
        var info = orderService.find(orderId);

        // Then
        assertAll(() -> {
            assertThat(info.orderId()).isEqualTo(orderId);
            assertThat(info.payMethod()).isEqualTo(PayMethod.TOSS);
            assertThat(info.orderStatus()).isEqualTo(OrderStatus.INIT);
            assertThat(info.deliveryFragment().receiverName()).isEqualTo("updated");
            assertThat(info.deliveryFragment().receiverPhone()).isEqualTo("updated");
            assertThat(info.deliveryFragment().receiverAddress1()).isEqualTo("updated");
            assertThat(info.deliveryFragment().receiverAddress2()).isEqualTo("updated");
            assertThat(info.deliveryFragment().receiverZipcode()).isEqualTo("updated");
            assertThat(info.deliveryFragment().etcMessage()).isEqualTo("updated");
            assertThat(info.receipt().totalPrice()).isEqualTo(9000);
            assertThat(info.receipt().items()).hasSize(1);
        });
    }

    @DisplayName("[주문-주문내역 조회")
    @Test
    void 주문내역을_조회한다() {
        // Given
        var userId = user.getId();
        orderService.create(ORDER_주문_요청(), userId);

        // When
        var infoList = orderService.findAllMine(userId);

        // Then
        assertThat(infoList).hasSize(1);
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

    @DisplayName("[주문-배송 정보 수정]")
    @Test
    void 배송정보를_수정한다() {
        // Given
        var orderId = order.getId();

        // When
        orderService.updateDeliveryFragment(ORDER_배송_정보_수정_요청(), orderId);

        // Then
        var deliveryFragment = order.getDeliveryFragment();
        assertAll(
            () -> assertThat(deliveryFragment.getReceiverName()).isEqualTo("updated"),
            () -> assertThat(deliveryFragment.getReceiverPhone()).isEqualTo("updated"),
            () -> assertThat(deliveryFragment.getReceiverAddress1()).isEqualTo("updated"),
            () -> assertThat(deliveryFragment.getReceiverAddress2()).isEqualTo("updated"),
            () -> assertThat(deliveryFragment.getReceiverZipcode()).isEqualTo("updated"),
            () -> assertThat(deliveryFragment.getEtcMessage()).isEqualTo("updated")
        );
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
            () -> assertThatThrownBy(() -> orderReader.getOrder(orderId))
                .isInstanceOf(OrderNotFoundException.class)
        );
    }
}