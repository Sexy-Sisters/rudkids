package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.user.domain.User;

public interface OrderReader {
    Order getOrder(User user);
}
