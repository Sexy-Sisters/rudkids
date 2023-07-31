package com.rudkids.core.collection.infrastructure;

import com.rudkids.core.collection.domain.Collection;
import com.rudkids.core.collection.domain.CollectionItem;
import com.rudkids.core.collection.domain.CollectionItemRepository;
import com.rudkids.core.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectionItemRepositoryImpl implements CollectionItemRepository {
    private final JpaCollectionItemRepository collectionItemRepository;

    @Override
    public void save(CollectionItem collectionItem) {
        collectionItemRepository.save(collectionItem);
    }

    @Override
    public CollectionItem getOrCreate(Collection collection, Item item) {
        return collectionItemRepository.findByItem(item)
            .orElseGet(() -> saveCollectionItem(collection, item));
    }

    private CollectionItem saveCollectionItem(Collection collection, Item item) {
        var collectionItem = CollectionItem.create(collection, item);
        return collectionItemRepository.save(collectionItem);
    }
}
