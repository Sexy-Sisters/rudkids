package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.domain.order.OrderCommand;

import java.util.UUID;

public interface OrderService {
    UUID create(OrderCommand.Register command, UUID userId);
}
