package com.rudkids.core.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class ProductFrontImage {

    @Column(name = "front_path")
    private String path;

    @Column(name = "front_url")
    private String url;

    @Column(name = "front_deleted")
    private boolean deleted;

    protected ProductFrontImage() {
    }

    private ProductFrontImage(String path, String url) {
        this.path = path;
        this.url = url;
        this.deleted = false;
    }

    public static ProductFrontImage create(String path, String url) {
        return new ProductFrontImage(path, url);
    }

    public void deleteImage() {
        deleted = true;
    }
}
