package com.rudkids.rudkids.domain.community;

import com.rudkids.rudkids.domain.community.domain.Community;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommunityReader {
    Community get(UUID communityId);
    List<Community> getCommunities(String type, Pageable pageable);
}
