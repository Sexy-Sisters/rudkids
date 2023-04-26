package com.rudkids.rudkids.domain.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
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
}
