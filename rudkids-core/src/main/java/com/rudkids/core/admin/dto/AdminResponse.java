package com.rudkids.core.admin.dto;

import com.rudkids.core.order.domain.Order;
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
        String deliveryTrackingNumber
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
                order.getDeliveryTrackingNumber()
            );
        }
    }

    public record OrderDetail(
        List<OrderItemResponse> orderItems,
        String receiverName,
        String receivedAddress,
        String orderStatus,
        String deliveryTrackingNumber
    ) {
        public OrderDetail(Order order) {
            this(
                order.getOrderItems().stream()
                    .map(OrderItemResponse::new)
                    .toList(),
                order.getDeliveryReceiverName(),
                order.getDeliveryReceivedAddress(),
                order.getOrderStatus(),
                order.getDeliveryTrackingNumber()
            );
        }
    }
}
