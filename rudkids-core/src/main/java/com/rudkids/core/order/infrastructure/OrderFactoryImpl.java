package com.rudkids.core.order.infrastructure;

import com.rudkids.core.cart.domain.CartItem;
import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.delivery.domain.DeliveryRepository;
import com.rudkids.core.order.domain.Order;
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
    public Order save(User user, OrderRequest.Create request) {
        var delivery = deliveryRepository.get(request.deliveryId());
        var cart = cartRepository.get(user);

        var order = Order.builder()
            .user(user)
            .delivery(delivery)
            .paymentMethod(request.paymentMethod())
            .totalPrice(cart.calculateSelectedCartItemsTotalPrice())
            .build();

        for (CartItem cartItem : cart.getSelectedCartItems()) {
            var orderItem = generateOrderItem(order, cartItem);
            order.addOrderItem(orderItem);
        }
        return order;
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
