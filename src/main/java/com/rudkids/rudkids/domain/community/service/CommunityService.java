package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.domain.community.CommunityCommand;
import com.rudkids.rudkids.domain.community.CommunityInfo;

import java.util.List;
import java.util.UUID;

public interface CommunityService {
    UUID create(UUID userId, CommunityCommand.Create command);
    List<CommunityInfo.Main> findAll(String type);
    CommunityInfo.Detail find(UUID communityId);
    void update(UUID userId, UUID communityId, CommunityCommand.Update command);
    void delete(UUID userId, UUID communityId);
}
