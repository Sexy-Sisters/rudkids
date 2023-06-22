package com.rudkids.core.communityLike.domain;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.user.domain.User;

public interface CommunityLikeRepository {
    void save(CommunityLike communityLike);
    void delete(Community community, User user);
    boolean hasCommunityLike(Community community, User user);
}
