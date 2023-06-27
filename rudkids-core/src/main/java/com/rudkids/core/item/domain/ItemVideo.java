package com.rudkids.core.item.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemVideo {

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "video_url")
    private String videoUrl;

    private ItemVideo(String imagePath, String imageUrl, String videoUrl) {
        this.imagePath = imagePath;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    public static ItemVideo create(String imagePath, String imageUrl, String videoUrl) {
        return new ItemVideo(imagePath, imageUrl, videoUrl);
    }
}
