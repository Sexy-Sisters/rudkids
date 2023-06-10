package com.rudkids.rudkids.domain.community;

import lombok.Builder;

public class CommunityInfo {

    @Builder
    public record Main(
        String title,
        String writer,
        String image
    ) {
    }

    @Builder
    public record Detail(
        String title,
        String content,
        String writer,
        String image,
        String writerProfileImage,
        int likeCount
    ) {
    }
}