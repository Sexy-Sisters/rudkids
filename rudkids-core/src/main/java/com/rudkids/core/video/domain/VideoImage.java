package com.rudkids.core.video.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoImage {
    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    private VideoImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static VideoImage create(String path, String url) {
        return new VideoImage(path, url);
    }
}
