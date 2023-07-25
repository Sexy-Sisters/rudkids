package com.rudkids.core.collection.dto;

import com.rudkids.core.collection.domain.CollectionItem;

public class CollectionItemResponse {

    public record Info(String itemName, String imageUrl) {
        public Info(CollectionItem collectionItem) {
            this(
                collectionItem.getName(),
                collectionItem.getImageUrl()
            );
        }
    }
}
