package com.rudkids.core.item.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_item_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemImage {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "item_image", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    private int ordering;

    private ItemImage(Item item, String path, String url, int ordering) {
        this.item = item;
        this.path = path;
        this.url = url;
        this.ordering = ordering;
    }

    public static ItemImage create(Item item, String path, String url, int ordering) {
        return new ItemImage(item, path, url, ordering);
    }
}
