package com.rudkids.rudkids.domain.item.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name = "tbl_item")
public class Item extends AbstractEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

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

    public String getName() {
        return name.getValue();
    }
}
