package com.rudkids.core.collection.domain;

import com.rudkids.core.collection.exception.CollectionItemCategoryNotFoundException;

import java.util.Arrays;

public enum CollectionItemCategory {
    ALL, TOY, STICKER, POSTER, MYSTERY;

    public static CollectionItemCategory toEnum(String category) {
        return Arrays.stream(values())
            .filter(it -> it.name().equalsIgnoreCase(category))
            .findFirst()
            .orElseThrow(CollectionItemCategoryNotFoundException::new);
    }
}
