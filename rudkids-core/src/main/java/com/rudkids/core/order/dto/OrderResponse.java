package com.rudkids.core.order.dto;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderDelivery;
import com.rudkids.core.order.domain.OrderStatus;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class OrderResponse {

    public record Create(UUID orderId) {}

    public record Main(
        UUID orderId,
        String createdAt,
        String orderStatus,
        List<OrderItemResponse> orderItems
    ) {
        public Main(Order order) {
            this(
                order.getId(),
                order.getCreatedAt(),
                order.getOrderStatus(),
                order.getOrderItems().stream()
                    .map(OrderItemResponse::new)
                    .toList()
            );
        }
    }

    @Builder
    public record Detail(
        UUID orderId,
        String createdAt,
        String orderStatus,
        List<OrderItemResponse> orderItems,
        DetailDelivery delivery,
        String paymentMethod
    ) {
        public Detail(Order order) {
            this(
                order.getId(),
                order.getCreatedAt(),
                order.getOrderStatus(),
                order.getOrderItems().stream()
                    .map(OrderItemResponse::new)
                    .toList(),
                new DetailDelivery(order.getDelivery()),
                order.getPaymentMethod()
            );
        }
    }

    public record DetailDelivery(
        String receiverName,
        String receiverPhone,
        String receivedAddress,
        String message
    ) {
        public DetailDelivery(OrderDelivery delivery) {
            this(
                delivery.getReceiverName(),
                delivery.getReceiverPhone(),
                delivery.getReceivedAddress(),
                delivery.getMessage()
            );
        }
    }
}
