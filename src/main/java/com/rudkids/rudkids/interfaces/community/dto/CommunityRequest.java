package com.rudkids.rudkids.interfaces.community.dto;

public class CommunityRequest {

    public record Create(
        String title,
        String content,
        String type
    ) {
    }

    public record Update(String title, String content) {
    }
}
