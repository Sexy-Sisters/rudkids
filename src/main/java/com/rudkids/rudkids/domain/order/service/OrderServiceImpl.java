package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.domain.cart.CartReader;
import com.rudkids.rudkids.domain.order.OrderCommand;
import com.rudkids.rudkids.domain.order.OrderMapper;
import com.rudkids.rudkids.domain.order.OrderReader;
import com.rudkids.rudkids.domain.order.OrderStore;
import com.rudkids.rudkids.domain.user.UserReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public void delete(UUID orderId) {
        var order = orderReader.getOrder(orderId);
        order.getCart().activate();
        orderStore.delete(order);
    }
}
