package com.rudkids.core.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class ProductBackImage {

    @Column(name = "back_path")
    private String path;

    @Column(name = "back_url")
    private String url;

    protected ProductBackImage() {
    }

    private ProductBackImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static ProductBackImage create(String path, String url) {
        return new ProductBackImage(path, url);
    }

    public boolean hasImage() {
        return !path.isBlank() && !url.isBlank();
    }
}