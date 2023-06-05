package com.rudkids.rudkids.domain.communityLike;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.user.domain.User;

public interface CommunityLikeReader {
    boolean hasLike(Community community, User user);
}
