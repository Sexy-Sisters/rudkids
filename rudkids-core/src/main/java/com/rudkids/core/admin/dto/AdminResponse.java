package com.rudkids.core.admin.dto;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.dto.OrderItemResponse;
import com.rudkids.core.user.domain.User;

import java.time.ZonedDateTime;
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
        String createdAt,
        OrderStatus orderStatus,
        List<OrderItemResponse> orderItems
    ) {
        public OrderInfo(Order order) {
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
}
