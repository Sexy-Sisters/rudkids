package com.rudkids.core.video.dto;

import com.rudkids.core.video.domain.Video;

public class VideoResponse {

    public record Info(
        String imageUrl,
        String videoBio,
        String name
    ) {
        public Info(Video video) {
            this(
                video.getImageUrl(),
                video.getBio(),
                video.getItemName()
            );
        }
    }

    public record Detail(
        String name,
        String videoUrl
    ) {
        public Detail(Video video) {
            this(
                video.getItemName(),
                video.getVideoUrl()
            );
        }
    }
}
