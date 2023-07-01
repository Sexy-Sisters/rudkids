package com.rudkids.core.item.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

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

    private ItemImage(Item item, String path, String url) {
        this.item = item;
        this.path = path;
        this.url = url;
    }

    public static ItemImage create(Item item, String path, String url) {
        return new ItemImage(item, path, url);
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }
}
