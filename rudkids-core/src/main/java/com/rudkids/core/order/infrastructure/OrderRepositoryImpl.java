package com.rudkids.core.order.infrastructure;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderDelivery;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.order.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final JpaOrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order get(UUID id) {
        return orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
