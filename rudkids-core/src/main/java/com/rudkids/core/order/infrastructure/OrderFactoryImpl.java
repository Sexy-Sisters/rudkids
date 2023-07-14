package com.rudkids.core.order.infrastructure;

import com.rudkids.core.cart.domain.Cart;
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

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderFactoryImpl implements OrderFactory {
    private final CartRepository cartRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public Order save(User user, OrderRequest.Create request) {
        var delivery = deliveryRepository.get(request.deliveryId());
        var cart = cartRepository.get(user);
        var order = Order.create(user, delivery, request.paymentMethod(), cart.calculateTotalPrice());
        var selectedCartItems = getSelectedCartItems(cart);

        for (CartItem cartItem : selectedCartItems) {
            var orderItem = generateOrderItem(order, cartItem);
            order.addOrderItem(orderItem);
        }
        return order;
    }

    private List<CartItem> getSelectedCartItems(Cart cart) {
        return cart.getCartItems().stream()
            .filter(CartItem::isSelected)
            .toList();
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
