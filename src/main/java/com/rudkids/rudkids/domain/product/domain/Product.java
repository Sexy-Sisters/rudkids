package com.rudkids.rudkids.domain.product.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.domain.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @Column(name = "product_id", columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @Embedded
    private Title title;

    @Embedded
    private Bio bio;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.OPEN;

    @Getter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Item> items = new ArrayList<>();

    protected Product() {
    }

    @Builder
    public Product(final Title title, final Bio bio) {
        this.title = title;
        this.bio = bio;
    }
}
