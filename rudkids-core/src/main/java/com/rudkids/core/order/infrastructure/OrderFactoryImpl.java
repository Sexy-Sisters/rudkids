package com.rudkids.core.order.infrastructure;

import com.rudkids.core.cart.domain.CartItem;
import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.delivery.domain.DeliveryRepository;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderDelivery;
import com.rudkids.core.order.domain.OrderItem;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.service.OrderFactory;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderFactoryImpl implements OrderFactory {
    private final CartRepository cartRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public Order save(User user, OrderRequest.OrderAndPayment request) {
        var delivery = deliveryRepository.getBasic(user);
        var cart = cartRepository.get(user);
        var orderDelivery = generateOrderDelivery(delivery);

        var order = Order.builder()
            .user(user)
            .delivery(orderDelivery)
            .totalPrice(cart.calculateSelectedCartItemsTotalPrice())
            .build();

        for (CartItem cartItem : cart.getSelectedCartItems()) {
            var orderItem = generateOrderItem(order, cartItem);
            order.addOrderItem(orderItem);
        }
        return order;
    }

    private OrderDelivery generateOrderDelivery(Delivery delivery) {
        return OrderDelivery.builder()
            .receiverName(delivery.getReceiverName())
            .receiverPhone(delivery.getReceiverPhone())
            .receivedAddress(delivery.getFullAddress())
            .message(delivery.getMessage())
            .build();
    }

    private OrderItem generateOrderItem(Order order, CartItem cartItem) {
        return OrderItem.builder()
            .order(order)
            .item(cartItem.getItem())
            .name(cartItem.getName())
            .imageUrl(cartItem.getImageUrl())
            .amount(cartItem.getAmount())
            .price(cartItem.getPrice())
            .build();
    }
}
