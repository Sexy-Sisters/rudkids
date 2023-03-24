package com.rudkids.rudkids.domain.item.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name = "tbl_item")
public class Item extends AbstractEntity {

    @Id
    @Column(name = "item_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @Embedded
    private Name name;

    @Embedded
    private Price price;

    @Embedded
    private Quantity quantity;

    @Enumerated(EnumType.STRING)
    private LimitType limitType;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus = ItemStatus.IN_STOCK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    protected Item() {
    }

    @Builder
    public Item(Name name, Price price, Quantity quantity, LimitType limitType) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.limitType = limitType;
    }

    void changeSoldOut() {
        this.itemStatus = ItemStatus.SOLD_OUT;
    }

    void changeInStock() {
        this.itemStatus = ItemStatus.IN_STOCK;
    }

    public void changeProduct(Product product) {
        this.product = product;
        product.getItems().add(this);
    }

    public String getName() {
        return this.name.getValue();
    }

    public int getPrice() {
        return this.price.getValue();
    }

    public int getQuantity() {
        return this.quantity.getValue();
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
}
