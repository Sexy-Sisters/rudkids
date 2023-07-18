package com.rudkids.core.order.infrastructure;

import com.rudkids.core.order.domain.CourierCompany;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderDelivery;
import com.rudkids.core.order.service.DeliveryTracker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryStateTracker implements DeliveryTracker {
    private final DeliveryStateTrackerClient deliveryStateTrackerClient;

    @Override
    public void changeCompletedState(List<Order> orders) {
        for(OrderDelivery delivery: toOrderDeliveries(orders)) {
            var response = deliveryStateTrackerClient.get(delivery.getTrackingNumber());
            var courierCompany = CourierCompany.create(response.getState());

            if(courierCompany.isCompletedState()) {
                delivery.changeStatusToComp();
            }
        }
    }

    private List<OrderDelivery> toOrderDeliveries(List<Order> orders) {
        return orders.stream()
            .map(Order::getDelivery)
            .filter(OrderDelivery::isDelivering)
            .toList();
    }
}
