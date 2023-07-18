package com.rudkids.core.video.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_video")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "video_id")
    private UUID id;

    @Embedded
    private VideoImage image;

    @Embedded
    private VideoBio videoBio;

    private String videoUrl;

    private String itemName;

    public Video(VideoImage image, VideoBio videoBio, String videoUrl, String itemName) {
        this.image = image;
        this.videoBio = videoBio;
        this.videoUrl = videoUrl;
        this.itemName = itemName;
    }

    public void update(VideoImage image, VideoBio videoBio, String videoUrl, String itemName) {
        this.image = image;
        this.videoBio = videoBio;
        this.videoUrl = videoUrl;
        this.itemName = itemName;
    }

    public String getImageUrl() {
        return image.getUrl();
    }

    public String getBio() {
        return videoBio.getValue();
    }
}
