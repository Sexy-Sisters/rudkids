package com.rudkids.core.communityLike.service;

import java.util.UUID;

public interface CommunityLikeService {
    void like(UUID userId, UUID communityId);
}
