package com.rudkids.core.item.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GrayImage {

    @Column(name = "gray_image_path")
    private String path;

    @Column(name = "gray_image_url")
    private String url;

    private GrayImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static GrayImage create(String path, String url) {
        return new GrayImage(path, url);
    }
}
