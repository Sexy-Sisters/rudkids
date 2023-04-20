package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.domain.order.OrderCommand;
import com.rudkids.rudkids.domain.order.OrderItemSeriesFactory;
import com.rudkids.rudkids.domain.order.OrderStore;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderStore orderStore;
    private final OrderItemSeriesFactory orderItemSeriesFactory;
    private final UserReader userReader;

    @Override
    public UUID create(UUID userId, OrderCommand.Register command) {
        var user = userReader.getUser(userId);
        var order = orderStore.store(command.toEntity());
        order.setRecipient(user);
        orderItemSeriesFactory.store(order, command);
        return order.getId();
    }
}
