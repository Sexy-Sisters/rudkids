package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.user.domain.User;

import java.util.UUID;

public interface OrderReader {
    Order getOrder(User user);
    Order getOrder(UUID orderId);
}
