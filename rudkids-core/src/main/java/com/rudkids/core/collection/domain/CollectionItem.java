package com.rudkids.core.collection.domain;

import com.rudkids.core.item.domain.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_collection_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "collection_item_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private CollectionItemStatus status = CollectionItemStatus.PRIVATE;

    private CollectionItem(Collection collection, Item item) {
        this.collection = collection;
        collection.addCollectionItem(this);
        this.item = item;
    }

    public static CollectionItem create(Collection collection, Item item) {
        return new CollectionItem(collection, item);
    }

    public String getName() {
        return item.getEnName();
    }

    public void bought() {
        status = CollectionItemStatus.PUBLIC;
    }

    public boolean isSameItem(Item item) {
        return this.item.equals(item);
    }

    public String getImageUrl() {
        if(status == CollectionItemStatus.PUBLIC) {
            return item.getFirstImageUrl();
        }
        return item.getGrayImageUrl();
    }

    public boolean isBought() {
        return status == CollectionItemStatus.PUBLIC;
    }
}
