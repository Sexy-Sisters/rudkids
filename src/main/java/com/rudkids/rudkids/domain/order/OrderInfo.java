package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.cart.CartItemInfo;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class OrderInfo {
    public record Main(UUID orderId, ZonedDateTime createdAt) {
    }

    @Builder
    public record Detail(
        UUID orderId,
        DeliveryInfo deliveryFragment,
        PayMethod payMethod,
        OrderStatus orderStatus,
        Receipt receipt
    ) {
        @Builder
        public record DeliveryInfo(
            String receiverName,
            String receiverPhone,
            String receiverZipcode,
            String receiverAddress1,
            String receiverAddress2,
            String etcMessage
        ) {
        }

        @Builder
        public record Receipt(
            int totalCartItemPrice,
            List<ItemInfo> items
        ) {
            @Builder
            public record ItemInfo(
                UUID itemId,
                String name,
                int price,
                int amount,
                List<CartItemOptionGroup> optionGroups,
                ItemStatus itemStatus
            ) {
                @Builder
                public record CartItemOptionGroup(String name, String optionName) {
                }
            }
        }
    }
}
