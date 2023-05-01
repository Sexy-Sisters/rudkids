package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.order.OrderReader;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.exception.OrderNotFoundException;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {
    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(User user) {
        return orderRepository.findByUser(user)
            .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order getOrder(UUID id) {
        return orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);
    }
}
