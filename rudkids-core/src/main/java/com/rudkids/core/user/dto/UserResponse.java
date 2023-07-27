package com.rudkids.core.user.dto;

import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.user.domain.User;
import lombok.Builder;

public class UserResponse {

    @Builder
    public record Info(
        String email,
        String name,
        String phoneNumber,
        ImageResponse.Info profileImage,
        int deliveringOrderCount,
        int boughtCollectionItemCount
    ) {
        public Info(User user, int boughtCollectionItemCount) {
            this(
                user.getEmail(),
                user.getName(),
                user.getPhoneNumber(),
                new ImageResponse.Info(user.getProfileImagePath(), user.getProfileImageUrl()),
                user.getDeliveringOrderCount(),
                boughtCollectionItemCount
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
                delivery.getAddress(),
                delivery.getExtraAddress(),
                delivery.getZipCode(),
                delivery.getMessage()
            );
        }
    }
}
