package com.rudkids.core.collection.infrastructure;

import com.rudkids.core.collection.domain.Collection;
import com.rudkids.core.collection.domain.CollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectionRepositoryImpl implements CollectionRepository {
    private final JpaCollectionRepository collectionRepository;

    @Override
    public Collection getOrCreate() {
        var collections = collectionRepository.findAll();
        if(collections.isEmpty()) {
            return saveCollection();
        }
        return collections.get(0);
    }

    private Collection saveCollection() {
        var collection = Collection.create();
        return collectionRepository.save(collection);
    }
}
