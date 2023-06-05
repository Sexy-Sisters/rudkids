package com.rudkids.rudkids.domain.communityLike;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.communityLike.domain.CommunityLike;
import com.rudkids.rudkids.domain.user.domain.User;

public interface CommunityLikeStore {
    void store(CommunityLike communityLike);
    void delete(Community community, User user);
}