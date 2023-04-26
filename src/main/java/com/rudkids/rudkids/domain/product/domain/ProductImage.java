package com.rudkids.rudkids.domain.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ProductImage {

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    protected ProductImage() {
    }

    private ProductImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static ProductImage create(String path, String url) {
        return new ProductImage(path, url);
    }
}
