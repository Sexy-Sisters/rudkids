package com.rudkids.core.collection.dto;

import com.rudkids.core.collection.domain.CollectionItem;
import com.rudkids.core.collection.domain.CollectionItemStatus;

public class CollectionItemResponse {

    public record Info(
        String itemName,
        String imageUrl,
        String grayImageUrl,
        CollectionItemStatus status
    ) {
        public Info(CollectionItem collectionItem) {
            this(
                collectionItem.getName(),
                collectionItem.getImageUrl(),
                collectionItem.getGrayImageUrl(),
                collectionItem.getStatus()
            );
        }
    }
}
