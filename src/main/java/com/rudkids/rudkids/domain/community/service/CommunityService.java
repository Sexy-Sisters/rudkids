package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.domain.community.CommunityCommand;
import com.rudkids.rudkids.domain.community.CommunityInfo;

import java.util.List;
import java.util.UUID;

public interface CommunityService {
    void create(UUID userId, CommunityCommand.Create command);
    List<CommunityInfo.Main> findAll(String type);
    CommunityInfo.Detail find(UUID magazineId);
    void update(UUID magazineId, CommunityCommand.Update command);
    void delete(UUID magazineId);
}
