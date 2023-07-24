package com.rudkids.core.collection.domain;

import com.rudkids.core.user.domain.User;

public interface CollectionRepository {

    Collection getOrCreate(User user);
}
