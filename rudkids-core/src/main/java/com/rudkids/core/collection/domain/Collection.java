package com.rudkids.core.collection.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_collection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Collection {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "collection_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CollectionItem> collectionItems = new ArrayList<>();

    public static Collection create() {
        return new Collection();
    }

    public void addCollectionItem(CollectionItem collectionItem) {
        collectionItems.add(collectionItem);
    }

    public int getCollectionItemSize() {
        return collectionItems.size();
    }
}
