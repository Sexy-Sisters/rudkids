package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.domain.cart.CartReader;
import com.rudkids.rudkids.domain.order.*;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import com.rudkids.rudkids.domain.order.exception.OrderStatusNotFoundException;
import com.rudkids.rudkids.domain.order.service.strategy.orderStatus.OrderStatusChangeStrategy;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderReader orderReader;
    private final OrderStore orderStore;
    private final OrderMapper orderMapper;
    private final UserReader userReader;
    private final CartReader cartReader;
    private final List<OrderStatusChangeStrategy> orderStatusChangeStrategies;

    @Override
    public UUID create(OrderCommand.CreateRequest command, UUID userId) {
        var user = userReader.getUser(userId);
        var cart = cartReader.getActiveCart(user);
        var initOrder = orderMapper.toEntity(command, cart);
        var order = orderStore.store(initOrder);
        order.addUser(user);
        cart.deactivate();
        return order.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderInfo.Detail find(UUID orderId) {
        var order = orderReader.getOrder(orderId);
        return orderMapper.toDetail(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderInfo.Main> findAll(Pageable pageable) {
        return orderReader.getOrders(pageable)
            .map(orderMapper::toInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderInfo.Main> findAllMine(UUID userId) {
        var user = userReader.getUser(userId);
        return user.getOrders().stream()
            .map(orderMapper::toInfo)
            .toList();
    }

    @Override
    public void changeStatus(OrderStatus orderStatus, UUID orderId) {
        var order = orderReader.getOrder(orderId);
        var strategy = findChangeStatusStrategy(orderStatus);
        strategy.changeStatus(order);
    }

    @Override
    public void updateDeliveryFragment(OrderCommand.UpdateDeliveryFragment command, UUID orderId) {
        var order = orderReader.getOrder(orderId);
        order.updateDeliveryFragment(
            command.receiverName(),
            command.receiverPhone(),
            command.receiverZipcode(),
            command.receiverAddress1(),
            command.receiverAddress2(),
            command.etcMessage()
        );
    }

    @Override
    public void delete(UUID orderId) {
        var order = orderReader.getOrder(orderId);
        order.validateNotPaid();
        order.activateCart();
        orderStore.delete(order);
    }

    private OrderStatusChangeStrategy findChangeStatusStrategy(OrderStatus orderStatus) {
        return orderStatusChangeStrategies.stream()
            .filter(strategy -> strategy.isOrderStrategy(orderStatus))
            .findFirst()
            .orElseThrow(OrderStatusNotFoundException::new);
    }
}
