package com.rudkids.core.order.service;

import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.delivery.domain.DeliveryRepository;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.OrderResponse;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public UUID create(UUID userId, OrderRequest.Create request) {
        var user = userRepository.getUser(userId);
        var cart = cartRepository.getActiveCart(user);
        var delivery = deliveryRepository.get(request.deliveryId());
        var order = Order.create(user, cart, delivery, request.payMethod());

        orderRepository.save(order);
        cart.deactivate();
        return order.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse.Detail get(UUID orderId) {
        var order = orderRepository.get(orderId);
        return new OrderResponse.Detail(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse.Main> getAllMine(UUID userId, Pageable pageable) {
        var user = userRepository.getUser(userId);
        return user.getOrders().stream()
            .map(OrderResponse.Main::new)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponse.Main> getAll(Pageable pageable) {
        return orderRepository.getOrders(pageable)
            .map(OrderResponse.Main::new);
    }

    @Override
    public void changeStatus(UUID orderId, OrderRequest.ChangeStatus request) {
        var order = orderRepository.get(orderId);
        var status = OrderStatus.toEnum(request.status());
        order.changeStatus(status);
    }

    @Override
    public void delete(UUID orderId) {
        var order = orderRepository.get(orderId);
        order.validateNotPaid();
        order.activateCart();
        orderRepository.delete(order);
    }
}
