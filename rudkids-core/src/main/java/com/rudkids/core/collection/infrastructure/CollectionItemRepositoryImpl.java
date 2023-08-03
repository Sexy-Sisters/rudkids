package com.rudkids.core.collection.infrastructure;

import com.rudkids.core.collection.domain.CollectionItem;
import com.rudkids.core.collection.domain.CollectionItemCategory;
import com.rudkids.core.collection.domain.CollectionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CollectionItemRepositoryImpl implements CollectionItemRepository {
    private final JpaCollectionItemRepository collectionItemRepository;

    @Override
    public void save(CollectionItem collectionItem) {
        collectionItemRepository.save(collectionItem);
    }

    @Override
    public List<CollectionItem> getByCategory(CollectionItemCategory category) {
        return collectionItemRepository.findByCategory(category);
    }
}
