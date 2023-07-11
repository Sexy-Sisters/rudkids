package com.rudkids.core.order.service;

import com.rudkids.core.cart.domain.CartItemRepository;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.OrderResponse;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
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
    private final OrderFactory orderFactory;
    private final CartItemRepository cartItemRepository;
    private final PaymentClientManager paymentClientManager;

    @Override
    public UUID order(UUID userId, OrderRequest.Create request) {
        var user = userRepository.getUser(userId);
        var order = orderFactory.save(user, request);
        order.removeQuantity();

        order.validateAmount(request.amount());
        paymentClientManager.confirm(request.paymentKey(), order.getPaymentOrderId(),  request.amount());

        order.order();
        cartItemRepository.deleteSelected();
        orderRepository.save(order);
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
    public List<OrderResponse.Main> getAll(UUID userId) {
        var user = userRepository.getUser(userId);
        return user.getOrders().stream()
            .map(OrderResponse.Main::new)
            .toList();
    }

    @Override
    public void cancel(UUID userId, UUID orderId, OrderRequest.Cancel request) {
        var user = userRepository.getUser(userId);
        var order = orderRepository.get(orderId);
        order.validateHasSameUser(user);

        paymentClientManager.cancel(request);
        order.cancel();
    }

    @Override
    public List<OrderResponse.Main> getCancelOrders(UUID userId) {
        var user = userRepository.getUser(userId);
        return user.getOrders().stream()
            .filter(Order::isCanceled)
            .map(OrderResponse.Main::new)
            .toList();
    }

    @Override
    public void changeStatus(UUID orderId, OrderRequest.ChangeStatus request) {
        var order = orderRepository.get(orderId);
        var status = OrderStatus.toEnum(request.status());
        order.changeStatus(status);
    }

    @Override
    public void delete(UUID userId, UUID orderId) {
        var user = userRepository.getUser(userId);
        var order = orderRepository.get(orderId);
        order.validateHasSameUser(user);
        orderRepository.delete(order);
    }
}
