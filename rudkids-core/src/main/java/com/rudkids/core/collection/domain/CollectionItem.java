package com.rudkids.core.collection.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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

    @Column(name = "item_image_url")
    private String itemImageUrl;

    @Column(name = "item_gray_image_url")
    private String itemGrayImageUrl;

    @Column(name = "item_en_name")
    private String itemEnName;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CollectionItemCategory category;

    @Builder
    public CollectionItem(Collection collection,
                          String itemImageUrl,
                          String itemGrayImageUrl,
                          String itemEnName,
                          CollectionItemCategory category
                          ) {
        this.collection = collection;
        collection.addCollectionItem(this);
        this.itemImageUrl = itemImageUrl;
        this.itemGrayImageUrl = itemGrayImageUrl;
        this.itemEnName = itemEnName;
        this.category = category;
    }
}
