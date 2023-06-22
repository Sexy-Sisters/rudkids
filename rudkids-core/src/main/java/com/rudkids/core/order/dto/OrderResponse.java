package com.rudkids.core.order.dto;

import com.rudkids.core.cart.domain.Cart;
import com.rudkids.core.cart.domain.CartItem;
import com.rudkids.core.cart.domain.CartItemOptionGroup;
import com.rudkids.core.delivery.dto.DeliveryResponse;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.domain.PayMethod;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class OrderResponse {

    public record Main(UUID orderId, ZonedDateTime createdAt) {
        public Main(Order order) {
            this(order.getId(), order.getCreatedAt());
        }
    }

    @Builder
    public record Detail(
        UUID orderId,
        DeliveryResponse.Info deliveryFragment,
        PayMethod payMethod,
        OrderStatus orderStatus,
        Receipt receipt
    ) {
        public Detail(Order order) {
            this(
                order.getId(),
                new DeliveryResponse.Info(order.getDelivery()),
                order.getPayMethod(),
                order.getOrderStatus(),
                new Receipt(order.getCart())
            );
        }
    }

    @Builder
    public record Receipt(
        int totalPrice,
        List<ReceiptItemInfo> items
    ) {
        public Receipt(Cart cart) {
            this(
                cart.calculateTotalPrice(),
                cart.getCartItems().stream()
                    .map(ReceiptItemInfo::new)
                    .toList()
            );
        }
    }

    @Builder
    public record ReceiptItemInfo(
        UUID itemId,
        String name,
        int price,
        int amount,
        List<ReceiptOptionGroup> optionGroups,
        ItemStatus itemStatus
    ) {
        public ReceiptItemInfo(CartItem cartItem) {
            this(
                cartItem.getId(),
                cartItem.getName(),
                cartItem.getPrice(),
                cartItem.getAmount(),
                cartItem.getCartItemOptionGroups().stream()
                    .map(ReceiptOptionGroup::new)
                    .toList(),
                cartItem.getItemStatus()
            );
        }
    }

    @Builder
    public record ReceiptOptionGroup(String name, String optionName) {
        public ReceiptOptionGroup(CartItemOptionGroup group) {
            this(group.getName(), group.getOptionName());
        }
    }
}
