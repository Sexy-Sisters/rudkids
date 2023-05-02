package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.domain.order.OrderCommand;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;

import java.util.UUID;

public interface OrderService {
    UUID create(OrderCommand.CreateRequest command, UUID userId);
    
    void delete(UUID orderId);

    void changeStatus(OrderStatus orderStatus, UUID orderId);

    void updateDeliveryFragment(OrderCommand.UpdateDeliveryFragment command, UUID orderId);
}
