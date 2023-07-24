package com.rudkids.core.collection.domain;

import com.rudkids.core.user.domain.User;

import java.util.List;

public interface CollectionRepository {
    List<Collection> getAll();
    Collection getOrCreate(User user);
}
