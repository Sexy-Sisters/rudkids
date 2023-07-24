package com.rudkids.core.collection.infrastructure;

import com.rudkids.core.collection.domain.Collection;
import com.rudkids.core.collection.domain.CollectionRepository;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectionRepositoryImpl implements CollectionRepository {
    private final JpaCollectionRepository collectionRepository;

    @Override
    public Collection getOrCreate(User user) {
        return collectionRepository.findByUser(user)
            .orElseGet(() -> saveCollection(user));
    }

    private Collection saveCollection(User user) {
        var collection = Collection.create(user);
        return collectionRepository.save(collection);
    }
}
