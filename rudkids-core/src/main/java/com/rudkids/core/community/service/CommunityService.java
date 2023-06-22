package com.rudkids.core.community.service;

import com.rudkids.core.community.dto.CommunityRequest;
import com.rudkids.core.community.dto.CommunityResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommunityService {
    UUID create(UUID userId, CommunityRequest.Create request);
    List<CommunityResponse.Main> getAll(String type, Pageable pageable);
    CommunityResponse.Detail get(UUID communityId);
    void update(UUID userId, UUID communityId, CommunityRequest.Update command);
    void delete(UUID userId, UUID communityId);
}
