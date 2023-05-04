package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.order.domain.DeliveryFragment;
import com.rudkids.rudkids.domain.order.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderCommand.CreateRequest command, Cart cart) {
        var deliveryFragment = DeliveryFragment.builder()
            .receiverName(command.receiverName())
            .receiverPhone(command.receiverPhone())
            .receiverZipcode(command.receiverZipcode())
            .receiverAddress1(command.receiverAddress1())
            .receiverAddress2(command.receiverAddress2())
            .etcMessage(command.etcMessage())
            .build();
        return Order.builder()
            .payMethod(command.payMethod())
            .deliveryFragment(deliveryFragment)
            .cart(cart)
            .build();
    }

    public OrderInfo.Main toInfo(Order order) {
        return new OrderInfo.Main(order.getId(), order.getCreatedAt());
    }
}
