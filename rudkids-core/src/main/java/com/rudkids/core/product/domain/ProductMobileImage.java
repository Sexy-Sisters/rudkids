package com.rudkids.core.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductMobileImage {

    @Column(name = "mobile_path")
    private String path;

    @Column(name = "mobile_url")
    private String url;

    private ProductMobileImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static ProductMobileImage create(String path, String url) {
        return new ProductMobileImage(path, url);
    }
}
