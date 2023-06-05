package com.rudkids.rudkids.domain.community;

import com.rudkids.rudkids.domain.community.domain.Community;

public interface CommunityStore {
    void store(Community community);
    void delete(Community community);
}
