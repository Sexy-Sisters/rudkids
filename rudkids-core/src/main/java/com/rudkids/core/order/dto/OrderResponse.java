package com.rudkids.core.order.dto;

import com.rudkids.core.delivery.dto.DeliveryResponse;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.domain.PayMethod;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class OrderResponse {

    public record PaymentInformation(
        String orderName,
        UUID orderId,
        UUID customerId
    ) {}

    public record Main(
        UUID orderId,
        ZonedDateTime createdAt,
        OrderStatus orderStatus,
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
        ZonedDateTime createdAt,
        OrderStatus orderStatus,
        List<OrderItemResponse> orderItems,
        DeliveryResponse.Info deliveryFragment,
        PayMethod payMethod
    ) {
        public Detail(Order order) {
            this(
                order.getId(),
                order.getCreatedAt(),
                order.getOrderStatus(),
                order.getOrderItems().stream()
                    .map(OrderItemResponse::new)
                    .toList(),
                new DeliveryResponse.Info(order.getDelivery()),
                order.getPayMethod()
            );
        }
    }
}
