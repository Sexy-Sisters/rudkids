package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.order.OrderStore;
import com.rudkids.rudkids.domain.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {
    private final OrderRepository orderRepository;

    @Override
    public Order store(Order order) {
        return orderRepository.save(order);
    }
}
