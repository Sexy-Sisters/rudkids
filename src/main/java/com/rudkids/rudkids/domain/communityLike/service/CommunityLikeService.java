package com.rudkids.rudkids.domain.communityLike.service;

import java.util.UUID;

public interface CommunityLikeService {
    void like(UUID userId, UUID communityId);
}
