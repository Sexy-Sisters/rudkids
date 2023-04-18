package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.order.OrderReader;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.exception.NotFoundOrderException;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {
    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(User user) {
        return orderRepository.findByUser(user)
            .orElseThrow(NotFoundOrderException::new);
    }
}
