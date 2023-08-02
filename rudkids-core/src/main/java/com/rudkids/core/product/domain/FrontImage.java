package com.rudkids.core.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class FrontImage {

    @Column(name = "front_path")
    private String path;

    @Column(name = "front_url")
    private String url;

    protected FrontImage() {
    }

    private FrontImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static FrontImage create(String path, String url) {
        return new FrontImage(path, url);
    }
}
