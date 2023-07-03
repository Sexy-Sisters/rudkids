package com.rudkids.core.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage {
    private static final String DEFAULT_PATH = "";

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    private ProfileImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public static ProfileImage create(String path, String url) {
        return new ProfileImage(path, url);
    }

    public static ProfileImage createDefault(String url) {
        return new ProfileImage(DEFAULT_PATH, url);
    }

    public boolean isEmptyPath() {
        return path.equals(DEFAULT_PATH);
    }
}
