package com.rudkids.rudkids.domain.product.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.domain.item.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "tbl_product")
public class Product {

    @Id
    @Column(name = "product_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @Embedded
    private Title title;

    @Embedded
    private ProductBio productBio;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.OPEN;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private final List<Item> items = new ArrayList<>();

    protected Product() {
    }

    @Builder
    public Product(final Title title, final ProductBio productBio) {
        this.title = title;
        this.productBio = productBio;
    }

    public void open() {
        this.productStatus = ProductStatus.OPEN;
    }

    public void close() {
        this.productStatus = ProductStatus.CLOSED;
    }

    public String getTitle() {
        return title.getValue();
    }

    public String getProductBio() {
        return productBio.getValue();
    }
}
