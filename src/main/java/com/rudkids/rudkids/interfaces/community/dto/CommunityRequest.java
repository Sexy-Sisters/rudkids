package com.rudkids.rudkids.interfaces.community.dto;

import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;

public class CommunityRequest {

    public record Create(
        String title,
        String content,
        String type,
        ImageRequest image
    ) {
    }

    public record Update(
        String title,
        String content,
        ImageRequest image
    ) {
    }
}
