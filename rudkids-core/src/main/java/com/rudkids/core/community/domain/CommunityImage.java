package com.rudkids.core.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityImage {

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    private boolean deleted;

    private CommunityImage(String path, String url) {
        this.path = path;
        this.url = url;
        this.deleted = false;
    }

    public static CommunityImage create(String path, String url) {
        return new CommunityImage(path, url);
    }

    public void deleteImage() {
        deleted = true;
    }
}
