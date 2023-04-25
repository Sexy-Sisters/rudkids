package com.rudkids.rudkids.domain.item.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.rudkids.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_item")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private final List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    @Builder
    public Item(Product product, Name name, ItemBio itemBio, Price price, Quantity quantity, LimitType limitType) {
        this.product = product;
        this.name = name;
        this.itemBio = itemBio;
        this.price = price;
        this.quantity = quantity;
        this.limitType = limitType;
        this.itemStatus = ItemStatus.SELLING;
    }

    public static Item create(Name name, ItemBio itemBio, Price price, Quantity quantity, LimitType limitType) {
        return Item.builder()
            .name(name)
            .itemBio(itemBio)
            .price(price)
            .quantity(quantity)
            .limitType(limitType)
            .build();
    }

    public void update(Name name, ItemBio itemBio, Price price, Quantity quantity, LimitType limitType) {
        this.name = name;
        this.itemBio = itemBio;
        this.price = price;
        this.quantity = quantity;
        this.limitType = limitType;
    }

    public void changePreparing() {
        this.itemStatus = ItemStatus.PREPARING;
    }

    public void changeSelling() {
        this.itemStatus = ItemStatus.SELLING;
    }

    public void changeSoldOut() {
        this.itemStatus = ItemStatus.SOLD_OUT;
    }

    public void setProduct(Product product) {
        this.product = product;
        product.getItems().add(this);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
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
}
