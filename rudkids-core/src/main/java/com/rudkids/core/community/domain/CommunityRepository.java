package com.rudkids.core.community.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommunityRepository {
    void save(Community community);
    List<Community> getCommunities(String type, Pageable pageable);
    List<String> getImagePaths();
    Community getCommunity(UUID id);
    void delete(Community community);
}