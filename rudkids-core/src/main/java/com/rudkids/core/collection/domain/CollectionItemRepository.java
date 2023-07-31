package com.rudkids.core.collection.domain;

import com.rudkids.core.item.domain.Item;

public interface CollectionItemRepository {
    void save(CollectionItem collectionItem);
    CollectionItem getOrCreate(Collection collection, Item item);
}
