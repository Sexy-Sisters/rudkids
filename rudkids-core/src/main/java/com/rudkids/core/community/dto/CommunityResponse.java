package com.rudkids.core.community.dto;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.image.dto.ImageResponse;
import lombok.Builder;

public class CommunityResponse {

    public record Main(
        String title,
        String writer,
        String image
    ) {
        public Main(Community community) {
            this(community.getTitle(), community.getWriter(), community.getUrl());
        }
    }

    @Builder
    public record Detail(
        String title,
        String content,
        String writer,
        ImageResponse.Info image,
        String writerProfileImage,
        int likeCount
    ) {
        public Detail(Community community) {
            this(
                community.getTitle(),
                community.getContent(),
                community.getWriter(),
                new ImageResponse.Info(community.getPath(), community.getUrl()),
                community.getWriterProfileImage(),
                community.getLikeCount()
            );
        }
    }
}
