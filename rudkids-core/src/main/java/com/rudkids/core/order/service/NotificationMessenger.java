package com.rudkids.core.order.service;

import com.rudkids.core.user.domain.User;

public interface NotificationMessenger {
    void sendOrderHistory(User user);
    void sendDeliveryCompleted(User user);
    void sendPaymentCancel();
}