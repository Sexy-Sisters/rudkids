package com.rudkids.core.item.domain;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.core.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "item_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private Name name;

    @Embedded
    private ItemBio itemBio;

    @Embedded
    private Price price;

    @Embedded
    private Quantity quantity;

    @Enumerated(EnumType.STRING)
    private LimitType limitType;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Embedded
    private ItemVideo video;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    private final List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ItemImage> images = new ArrayList<>();

    @Builder
    public Item(Product product, Name name, ItemBio itemBio, Price price,
                Quantity quantity, LimitType limitType, ItemVideo video) {
        this.product = product;
        this.name = name;
        this.itemBio = itemBio;
        this.price = price;
        this.quantity = quantity;
        this.limitType = limitType;
        this.itemStatus = ItemStatus.SELLING;
        this.video = video;
    }

    public void testUpdate(Name name) {
        this.name = name;
    }

    public void update(Name name, ItemBio itemBio, Price price, Quantity quantity, LimitType limitType) {
        this.name = name;
        this.itemBio = itemBio;
        this.price = price;
        this.quantity = quantity;
        this.limitType = limitType;
    }

    public void changeStatus(ItemStatus status) {
        this.itemStatus = status;
    }

    public void addOptionGroup(ItemOptionGroup itemOptionGroup) {
        itemOptionGroups.add(itemOptionGroup);
    }

    public void addImage(ItemImage image) {
        images.add(image);
    }

    public void setProduct(Product product) {
        this.product = product;
        product.addItem(this);
    }

    public UUID getId() {
        return id;
    }

    public String getEnName() {
        return name.getEnName();
    }

    public String getKoName() {
        return name.getKoName();
    }

    public int getPrice() {
        return price.getValue();
    }

    public int getQuantity() {
        return quantity.getValue();
    }

    public String getItemBio() {
        return itemBio.getValue();
    }

    public LimitType getLimitType() {
        return limitType;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public Product getProduct() {
        return product;
    }

    public List<ItemOptionGroup> getItemOptionGroups() {
        return itemOptionGroups;
    }

    public List<String> getImageUrls() {
        return images.stream()
            .map(ItemImage::getUrl)
            .toList();
    }

    public void deleteItemImage() {
        for(ItemImage image: images) {
            image.deleteImage();
        }
    }

    public String getCartItemImageUrl() {
        return images.get(0).getUrl();
    }

    public String getVideoImageUrl() {
        return video.getImageUrl();
    }

    public String getVideoUrl() {
        return video.getVideoUrl();
    }
}
