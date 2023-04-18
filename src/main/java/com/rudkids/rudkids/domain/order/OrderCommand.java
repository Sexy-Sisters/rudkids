package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.order.domain.DeliveryFragment;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.domain.orderItem.OrderItem;
import com.rudkids.rudkids.domain.order.domain.orderItem.OrderItemOption;
import com.rudkids.rudkids.domain.order.domain.orderItem.OrderItemOptionGroup;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class OrderCommand {

    @Builder
    public record Register(
        String payMethod,
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddress1,
        String receiverAddress2,
        String etcMessage,
        List<RegisterOrderItem> orderItemList
    ) {
        public Order toEntity(User user) {
            var deliveryFragment = DeliveryFragment.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverZipcode(receiverZipcode)
                .receiverAddress1(receiverAddress1)
                .receiverAddress2(receiverAddress2)
                .etcMessage(etcMessage)
                .build();

            return Order.builder()
                .user(user)
                .payMethod(payMethod)
                .deliveryFragment(deliveryFragment)
                .build();
        }
    }

    @Builder
    public record RegisterOrderItem(
        UUID itemId,
        Integer orderCount,
        String itemName,
        Long itemPrice,
        List<RegisterOrderItemOptionGroup> orderItemOptionGroupList
    ) {
        public OrderItem toEntity(Order order, Item item) {
            return OrderItem.builder()
                .order(order)
                .orderCount(orderCount)
                .item(item)
                .itemPrice(item.getPrice())
                .itemName(item.getName())
                .build();
        }
    }

    @Builder
    public record RegisterOrderItemOptionGroup(
        Integer ordering,
        String itemOptionGroupName,
        List<RegisterOrderItemOption> orderItemOptionList
    ) {
        public OrderItemOptionGroup toEntity(OrderItem orderItem) {
            return OrderItemOptionGroup.builder()
                .orderItem(orderItem)
                .ordering(ordering)
                .itemOptionGroupName(itemOptionGroupName)
                .build();
        }
    }

    @Builder
    public record RegisterOrderItemOption(
        Integer ordering,
        String itemOptionName,
        Long itemOptionPrice
    ) {
        public OrderItemOption toEntity(OrderItemOptionGroup orderItemOptionGroup) {
            return OrderItemOption.builder()
                .orderItemOptionGroup(orderItemOptionGroup)
                .ordering(ordering)
                .itemOptionName(itemOptionName)
                .itemOptionPrice(itemOptionPrice)
                .build();
        }
    }
}
