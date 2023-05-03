package com.rudkids.rudkids.domain.order.service.strategy.orderStatus;

import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class DeliveryCompleteStrategy implements OrderStatusChangeStrategy {

    @Override
    public boolean isOrderStrategy(OrderStatus orderStatus) {
        return OrderStatus.DELIVERY_COMPLETE == orderStatus;
    }

    @Override
    public void changeStatus(Order order) {
        order.completeDelivery();
    }
}
