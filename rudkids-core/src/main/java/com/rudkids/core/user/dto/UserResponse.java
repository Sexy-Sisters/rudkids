package com.rudkids.core.user.dto;

import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.user.domain.User;
import lombok.Builder;

public class UserResponse {

    @Builder
    public record Info(
        String email,
        String name,
        String phoneNumber,
        String profileImage
    ) {
        public Info(User user) {
            this(
                user.getEmail(),
                user.getName(),
                user.getPhoneNumber(),
                user.getProfileImageUrl()
            );
        }
    }

    @Builder
    public record Address(
        String receiverName,
        String receiverPhone,
        String receiverAddress1,
        String receiverAddress2,
        String receiverZipCode,
        String message
    ) {
        public Address(Delivery delivery) {
            this(
                delivery.getReceiverName(),
                delivery.getReceiverPhone(),
                delivery.getAddress1(),
                delivery.getAddress2(),
                delivery.getZipCode(),
                delivery.getMessage()
            );
        }
    }
}
