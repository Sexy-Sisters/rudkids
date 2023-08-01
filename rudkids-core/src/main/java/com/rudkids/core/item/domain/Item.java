package com.rudkids.core.item.domain;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.item.domain.option.ItemOption;
import com.rudkids.core.item.domain.optionGroup.ItemOptionGroup;
import com.rudkids.core.item.exception.QuantityDepletedException;
import com.rudkids.core.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "item_id", columnDefinition = "BINARY(16)")
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

    @Embedded
    private GrayImage grayImage;

    @Enumerated(EnumType.STRING)
    private LimitType limitType;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    private String videoUrl;

    @Column(name = "mystery_item_name")
    private String mysteryItemName;

    @Column(name = "mystery")
    private boolean mystery;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    private final List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ItemImage> images = new ArrayList<>();

    @Builder
    public Item(Product product,
                Name name,
                ItemBio itemBio,
                Price price,
                Quantity quantity,
                LimitType limitType,
                GrayImage grayImage,
                String videoUrl,
                String mysteryItemName,
                boolean mystery
    ) {
        this.product = product;
        this.name = name;
        this.itemBio = itemBio;
        this.price = price;
        this.quantity = quantity;
        this.limitType = limitType;
        this.itemStatus = ItemStatus.SELLING;
        this.grayImage = grayImage;
        this.videoUrl = videoUrl;
        this.mysteryItemName = mysteryItemName;
        this.mystery = mystery;
    }

    public void update(Name name,
                       ItemBio itemBio,
                       Price price,
                       Quantity quantity,
                       LimitType limitType,
                       GrayImage grayImage,
                       String videoUrl) {
        this.name = name;
        this.itemBio = itemBio;
        this.price = price;
        this.quantity = quantity;
        this.limitType = limitType;
        this.grayImage = grayImage;
        this.videoUrl = videoUrl;
    }

    public void changeStatus(ItemStatus status) {
        this.itemStatus = status;
    }

    public List<ItemOption> getItemOptions() {
        return itemOptionGroups.stream()
            .flatMap(itemOptionGroup -> itemOptionGroup.getItemOptions().stream())
            .toList();
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

    public String getGrayImageUrl() {
        return grayImage.getUrl();
    }

    public String getGrayImagePath() {
        return grayImage.getPath();
    }

    public List<String> getImagePaths() {
        return images.stream()
            .map(ItemImage::getPath)
            .toList();
    }

    public String getFirstImageUrl() {
        return images.stream()
            .filter(ItemImage::isFirstOrdering)
            .findFirst()
            .map(ItemImage::getUrl)
            .orElse("");
    }

    public void removeQuantity(int quantity) {
        int restQuantity = this.quantity.getRestQuantity(quantity);
        if(restQuantity < 0) {
            itemStatus = ItemStatus.SOLD_OUT;
            throw new QuantityDepletedException();
        }

        this.quantity.remove(restQuantity);
    }

    public void addQuantity(int amount) {
        quantity.add(amount);
    }

    public UUID getProductId() {
        return product.getId();
    }

    public boolean isSoldOut() {
        return itemStatus == ItemStatus.SOLD_OUT;
    }
}