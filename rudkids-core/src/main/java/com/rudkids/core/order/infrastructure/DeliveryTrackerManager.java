package com.rudkids.core.order.infrastructure;

import com.rudkids.core.collection.domain.CollectionItem;
import com.rudkids.core.collection.domain.CollectionItemRepository;
import com.rudkids.core.collection.domain.CollectionRepository;
import com.rudkids.core.order.domain.CourierCompany;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderItem;
import com.rudkids.core.order.service.DeliveryTracker;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryTrackerManager implements DeliveryTracker {
    private final DeliveryTrackerClient deliveryTrackerClient;
    private final CollectionRepository collectionRepository;
    private final CollectionItemRepository collectionItemRepository;

    @Override
    public void changeCompletedState(User user) {
        for(Order order: user.getOrders()) {
            var delivery = order.getDelivery();
            var response = deliveryTrackerClient.get(delivery.getTrackingNumber());
            var courierCompany = CourierCompany.create(response.getState());

            if(courierCompany.isCompletedState()) {
                delivery.changeStatusToComp();
                saveCollectionItem(user, order);
            }
        }
    }

    private void saveCollectionItem(User user, Order order) {
        var collection = collectionRepository.getOrCreate(user);
        var orderItems = order.getOrderItems();

        for(OrderItem orderItem: orderItems) {
            var collectionItem = collection.getCollectionItem(orderItem.getItem());
            collectionItem.bought();
            collection.addCollectionItem(collectionItem);
            collectionItemRepository.save(collectionItem);
        }
    }

    @Override
    public void validateHasDeliveryTrackingNumber(String deliveryTrackingNumber) {
        var response = deliveryTrackerClient.get(deliveryTrackingNumber);
        var courierCompany = CourierCompany.create(response.message());
        courierCompany.validateDeliveryTrackingNumber();
    }
}
