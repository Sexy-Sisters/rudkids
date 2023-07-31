package com.rudkids.core.collection.domain;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.user.domain.User;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CollectionItem> collectionItems = new ArrayList<>();

    private Collection(User user) {
        this.user = user;
    }

    public static Collection create(User user) {
        return new Collection(user);
    }

    public void addCollectionItem(CollectionItem collectionItem) {
        collectionItems.add(collectionItem);
    }

    public boolean hasSameCollectionItemSize(int size) {
        return collectionItems.size() == size;
    }

    public boolean isAlreadyHasCollectionItem(Item item) {
        return collectionItems.stream()
            .anyMatch(collectionItem -> collectionItem.isSameItem(item));
    }

    public int getCollectionItemSize() {
        return collectionItems.stream()
            .filter(CollectionItem::isBought)
            .toList().size();
    }
}
