package com.rudkids.core.community.dto;

import com.rudkids.core.community.domain.Community;
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
        String image,
        String writerProfileImage,
        int likeCount
    ) {
        public Detail(Community community) {
            this(
                community.getTitle(),
                community.getContent(),
                community.getWriter(),
                community.getUrl(),
                community.getWriterProfileImage(),
                community.getLikeCount()
            );
        }
    }
}
