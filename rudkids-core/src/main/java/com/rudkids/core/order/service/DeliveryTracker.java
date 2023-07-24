package com.rudkids.core.order.service;

import com.rudkids.core.user.domain.User;

public interface DeliveryTracker {
    void changeCompletedState(User user);
    void validateHasDeliveryTrackingNumber(String deliveryTrackingNumber);
}