package com.rudkids.rudkids.domain.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ProductFrontImage {

    @Column(name = "front_path")
    private String path;

    @Column(name = "front_url")
    private String url;

    protected ProductFrontImage() {
    }

    private ProductFrontImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static ProductFrontImage create(String path, String url) {
        return new ProductFrontImage(path, url);
    }
}
