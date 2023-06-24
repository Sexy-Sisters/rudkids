package com.rudkids.core.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductBackImage {

    @Column(name = "back_path")
    private String path;

    @Column(name = "back_url")
    private String url;

    @Column(name = "back_deleted")
    private boolean deleted;

    private ProductBackImage(String path, String url) {
        this.path = path;
        this.url = url;
        this.deleted = false;
    }

    public static ProductBackImage create(String path, String url) {
        return new ProductBackImage(path, url);
    }

    public void deleteImage() {
        deleted = true;
    }
}
