package com.rudkids.core.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BackImage {

    @Column(name = "back_path")
    private String path;

    @Column(name = "back_url")
    private String url;

    private BackImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static BackImage create(String path, String url) {
        return new BackImage(path, url);
    }
}
