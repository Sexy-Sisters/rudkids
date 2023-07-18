package com.rudkids.core.admin.dto;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderDelivery;
import com.rudkids.core.order.dto.OrderItemResponse;
import com.rudkids.core.user.domain.User;

import java.util.List;
import java.util.UUID;

public class AdminResponse {

    public record UserSearchInfo(
        String email,
        String name,
        String phoneNumber,
        String profileImageUrl,
        String roleType
    ) {
        public UserSearchInfo(User user) {
            this(
                user.getEmail(),
                user.getName(),
                user.getPhoneNumber(),
                user.getProfileImageUrl(),
                user.getRoleType().name()
            );
        }
    }

    public record OrderInfo(
        UUID orderId,
        String customerName,
        int amount,
        List<OrderItemResponse> orderItems,
        String orderStatus,
        String createdAt,
        OrderDeliveryInfo delivery
    ) {
        public OrderInfo(Order order) {
            this(
                order.getId(),
                order.getCustomerName(),
                order.getTotalPrice(),
                order.getOrderItems().stream()
                    .map(OrderItemResponse::new)
                    .toList(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                new OrderDeliveryInfo(order.getDelivery())
            );
        }
    }

    public record OrderDeliveryInfo(
        String receiverName,
        String receivedAddress,
        String deliveryStatus,
        String deliveryTrackingNumber
    ) {
        public OrderDeliveryInfo(OrderDelivery delivery) {
            this(
                delivery.getReceiverName(),
                delivery.getReceivedAddress(),
                delivery.getStatus(),
                delivery.getTrackingNumber()
            );
        }
    }
}
