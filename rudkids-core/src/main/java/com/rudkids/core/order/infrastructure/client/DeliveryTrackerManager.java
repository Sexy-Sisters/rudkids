package com.rudkids.core.order.infrastructure.client;

import com.rudkids.core.admin.exception.DeliveryTrackingFailException;
import com.rudkids.core.order.domain.CourierCompany;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.service.DeliveryTracker;
import com.rudkids.core.user.domain.User;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryTrackerManager implements DeliveryTracker {
    private final DeliveryTrackerClient deliveryTrackerClient;

    @Override
    public boolean isDeliveryCompleted(User user, Order order) {
        try {
            var response = deliveryTrackerClient.get(order.getDeliveryTrackingNumber());
            var courierCompany = CourierCompany.create(response.getState());
            return courierCompany.isCompletedState();
        } catch (FeignException e) {
            if(e.status() == 404) {
                return false;
            }
            throw new DeliveryTrackingFailException();
        }
    }

    @Override
    public void validateHasDeliveryTrackingNumber(String deliveryTrackingNumber) {
//        var response = deliveryTrackerClient.get(deliveryTrackingNumber);
//        var courierCompany = CourierCompany.create(response.message());
//        courierCompany.validateDeliveryTrackingNumber();
    }
}
