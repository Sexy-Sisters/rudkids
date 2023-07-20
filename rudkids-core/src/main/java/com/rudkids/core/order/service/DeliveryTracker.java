package com.rudkids.core.order.service;

import com.rudkids.core.order.domain.Order;

import java.util.List;

public interface DeliveryTracker {
    void changeCompletedState(List<Order> orders);
    void validateHasDeliveryTrackingNumber(String deliveryTrackingNumber);
}