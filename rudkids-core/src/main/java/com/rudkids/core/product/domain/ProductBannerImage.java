package com.rudkids.core.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductBannerImage {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "product_banner_image_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private ProductBannerImage(Product product, String path, String url) {
        this.product = product;
        this.path = path;
        this.url = url;
    }

    public static ProductBannerImage create(Product product, String path, String url) {
        return new ProductBannerImage(product, path, url);
    }
}
