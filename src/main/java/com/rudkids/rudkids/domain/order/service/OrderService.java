package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.domain.order.OrderCommand;

import java.util.UUID;

public interface OrderService {
    UUID create(UUID userId, OrderCommand.Register command);
}
