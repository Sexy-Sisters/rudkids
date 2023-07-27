package com.rudkids.core.order.service;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.user.domain.User;

public interface DeliveryTracker {
    boolean isDeliveryCompleted(User user, Order order);
    void validateHasDeliveryTrackingNumber(String deliveryTrackingNumber);
}