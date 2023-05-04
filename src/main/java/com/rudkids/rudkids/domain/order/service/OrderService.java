package com.rudkids.rudkids.domain.order.service;

import com.rudkids.rudkids.domain.order.OrderCommand;
import com.rudkids.rudkids.domain.order.OrderInfo;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    UUID create(OrderCommand.CreateRequest command, UUID userId);
    
    List<OrderInfo.Main> findAll(UUID userId);

    void changeStatus(OrderStatus orderStatus, UUID orderId);

    void updateDeliveryFragment(OrderCommand.UpdateDeliveryFragment command, UUID orderId);

    void delete(UUID orderId);
}
