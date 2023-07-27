package com.rudkids.core.collection.service;

import com.rudkids.core.collection.domain.CollectionItemRepository;
import com.rudkids.core.collection.domain.CollectionRepository;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderItem;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Collector {
    private final CollectionRepository collectionRepository;
    private final CollectionItemRepository collectionItemRepository;

    public void collect(User user, Order order) {
        var collection = collectionRepository.getOrCreate(user);
        var orderItems = order.getOrderItems();

        for(OrderItem orderItem: orderItems) {
            var collectionItem = collection.getCollectionItem(orderItem.getItem());
            collectionItem.bought();
            collection.addCollectionItem(collectionItem);
            collectionItemRepository.save(collectionItem);
        }
    }
}
