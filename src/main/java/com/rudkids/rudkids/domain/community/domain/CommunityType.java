package com.rudkids.rudkids.domain.community.domain;

import com.rudkids.rudkids.domain.community.exception.CommunityTypeNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum CommunityType {
    MAGAZINE("MAGAZINE"),
    POST("POST");

    private final String description;

    public static CommunityType toEnum(String type) {
        return Arrays.stream(values())
            .filter(communityType -> communityType.isSameDescription(type))
            .findFirst()
            .orElseThrow(CommunityTypeNotFoundException::new);
    }

    private boolean isSameDescription(String type) {
        return description.equals(type);
    }
}
