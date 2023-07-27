package com.rudkids.core.order.service;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.user.domain.User;

public interface OrderFactory {
    Order save(User user, OrderRequest.OrderAndPayment request);
}
