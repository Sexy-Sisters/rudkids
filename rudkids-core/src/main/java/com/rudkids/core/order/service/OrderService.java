package com.rudkids.core.order.service;

import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    UUID order(UUID userId, OrderRequest.Create request);
    OrderResponse.Detail get(UUID orderId);
    List<OrderResponse.Main> getAll(UUID userId);
    void cancel(UUID userId, UUID orderId, OrderRequest.Cancel request);
    List<OrderResponse.Main> getCancelOrders(UUID userId);
    void changeStatus(UUID orderId, OrderRequest.ChangeStatus request);

    void delete(UUID userId, UUID orderId);

}
