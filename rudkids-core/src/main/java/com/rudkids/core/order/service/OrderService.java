package com.rudkids.core.order.service;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.OrderResponse;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final DeliveryTracker deliveryTracker;

    public OrderResponse.Create order(UUID userId, OrderRequest.Create request) {
        var user = userRepository.getUser(userId);
        var order = orderFactory.save(user, request);

        deleteNotOrderCompleted(user);
        orderRepository.save(order);
        return new OrderResponse.Create(order.getId());
    }

    private void deleteNotOrderCompleted(User user) {
        for(Order order: user.getOrders()) {
            if(order.isOrdering()) {
                orderRepository.delete(order);
            }
        }
    }

    @Transactional(readOnly = true)
    public OrderResponse.Detail get(UUID orderId) {
        var order = orderRepository.get(orderId);
        return new OrderResponse.Detail(order);
    }

    public List<OrderResponse.Main> getAll(UUID userId) {
        var user = userRepository.getUser(userId);
//        deliveryTracker.changeCompletedState(user);

        return user.getOrders().stream()
            .filter(order -> !order.isOrdering())
            .map(OrderResponse.Main::new)
            .toList();
    }

    public void cancel(UUID userId, UUID orderId) {
        var user = userRepository.getUser(userId);
        var order = orderRepository.get(orderId);
        order.validateHasSameUser(user);
        order.changeCancelling();
    }

    public List<OrderResponse.Main> getCancelOrders(UUID userId) {
        var user = userRepository.getUser(userId);
        return user.getOrders().stream()
            .filter(Order::isCanceled)
            .map(OrderResponse.Main::new)
            .toList();
    }

    public void changeStatus(UUID orderId, OrderRequest.ChangeStatus request) {
        var order = orderRepository.get(orderId);
        var status = OrderStatus.toEnum(request.status());
        order.changeStatus(status);
    }

    public void delete(UUID userId, UUID orderId) {
        var user = userRepository.getUser(userId);
        var order = orderRepository.get(orderId);
        order.validateHasSameUser(user);
        orderRepository.delete(order);
    }
}
