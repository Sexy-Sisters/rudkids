package com.rudkids.core.collection.service;

import com.rudkids.core.collection.domain.Collection;
import com.rudkids.core.collection.domain.CollectionItem;
import com.rudkids.core.collection.domain.CollectionItemRepository;
import com.rudkids.core.collection.domain.CollectionRepository;
import com.rudkids.core.collection.dto.CollectionItemResponse;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;
    private final CollectionItemRepository collectionItemRepository;
    private final ItemRepository itemRepository;

    public List<CollectionItemResponse.Info> getAll(UUID userId) {
        var user = userRepository.getUser(userId);
        var collection = collectionRepository.getOrCreate(user);
        var items = itemRepository.getAll();
        checkCollectionItems(collection, items);

        return collection.getCollectionItems().stream()
            .map(CollectionItemResponse.Info::new)
            .toList();
    }

    private void checkCollectionItems(Collection collection, List<Item> items) {
        if(!collection.hasSameItemSize(items.size())) {
            for(Item item: items) {
                if(!collection.isAlreadyHasCollectionItem(item)) {
                    var collectionItem = CollectionItem.create(collection, item);
                    collection.addCollectionItem(collectionItem);
                    collectionItemRepository.save(collectionItem);
                }
            }
        }
    }
}
