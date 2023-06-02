package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.order.OrderInfo.Detail;
import com.rudkids.rudkids.domain.order.OrderInfo.Detail.DeliveryInfo;
import com.rudkids.rudkids.domain.order.OrderInfo.Detail.Receipt;
import com.rudkids.rudkids.domain.order.OrderInfo.Detail.Receipt.ItemInfo;
import com.rudkids.rudkids.domain.order.OrderInfo.Detail.Receipt.ItemInfo.OptionGroup;
import com.rudkids.rudkids.domain.order.OrderInfo.Main;
import com.rudkids.rudkids.domain.order.domain.DeliveryFragment;
import com.rudkids.rudkids.domain.order.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderCommand.CreateRequest command, Cart cart) {
        return Order.builder()
            .payMethod(command.payMethod())
            .cart(cart)
            .build();
    }

    public Main toInfo(Order order) {
        return new Main(order.getId(), order.getCreatedAt());
    }

    public Detail toDetail(Order order) {
        var deliveryInfo = toInfo(order.getDeliveryFragment());
        var receipt = toInfo(order.getCart());

        return Detail.builder()
            .orderId(order.getId())
            .deliveryFragment(deliveryInfo)
            .payMethod(order.getPayMethod())
            .orderStatus(order.getOrderStatus())
            .receipt(receipt)
            .build();
    }

    public DeliveryInfo toInfo(DeliveryFragment deliveryFragment) {
        return DeliveryInfo.builder()
            .receiverName(deliveryFragment.getReceiverName())
            .receiverPhone(deliveryFragment.getReceiverPhone())
            .receiverAddress1(deliveryFragment.getReceiverAddress1())
            .receiverAddress2(deliveryFragment.getReceiverAddress2())
            .receiverZipcode(deliveryFragment.getReceiverZipcode())
            .etcMessage(deliveryFragment.getEtcMessage())
            .build();
    }

    public Receipt toInfo(Cart cart) {
        var items = cart.getCartItems().stream()
            .map(this::toInfo)
            .toList();
        return Receipt.builder()
            .totalPrice(cart.calculateTotalPrice())
            .items(items)
            .build();
    }

    public ItemInfo toInfo(CartItem cartItem) {
        var optionGroups = cartItem.getCartItemOptionGroups().stream()
            .map(optionGroup ->
                new OptionGroup(optionGroup.getName(), optionGroup.getOptionName())
            )
            .toList();

        return ItemInfo.builder()
            .itemId(cartItem.getId())
            .name(cartItem.getName())
            .price(cartItem.getPrice())
            .amount(cartItem.getAmount())
            .itemStatus(cartItem.getItemStatus())
            .optionGroups(optionGroups)
            .build();
    }
}
