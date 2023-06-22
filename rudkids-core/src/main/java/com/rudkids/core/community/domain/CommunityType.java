package com.rudkids.core.community.domain;

import com.rudkids.core.community.exception.CommunityTypeNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
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
