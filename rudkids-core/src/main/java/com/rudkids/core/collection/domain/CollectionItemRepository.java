package com.rudkids.core.collection.domain;

import java.util.List;

public interface CollectionItemRepository {
    void save(CollectionItem collectionItem);
    List<CollectionItem> getByCategory(CollectionItemCategory category);
}
