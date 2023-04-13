package com.rudkids.rudkids.domain.item.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.rudkids.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
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
    private ItemStatus itemStatus = ItemStatus.ON_SALES;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private final List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    protected Item() {
    }

    @Builder
    public Item(final Name name, final ItemBio itemBio, final Price price, final Quantity quantity, final LimitType limitType) {
        this.name = name;
        this.itemBio = itemBio;
        this.price = price;
        this.quantity = quantity;
        this.limitType = limitType;
    }

    public void changePrepare() {
        this.itemStatus = ItemStatus.PREPARE;
    }

    public void changeOnSales() {
        this.itemStatus = ItemStatus.ON_SALES;
    }

    public void changeEndOfSales() {
        this.itemStatus = ItemStatus.END_OF_SALES;
    }


    public void changeProduct(Product product) {
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

    public LimitType getLimitType() {
        return limitType;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public Product getProduct() {
        return product;
    }

    public String getItemBio() {
        return itemBio.getValue();
    }

    public List<ItemOptionGroup> getItemOptionGroups() {
        return itemOptionGroups;
    }
}
