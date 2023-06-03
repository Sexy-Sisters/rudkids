package com.rudkids.rudkids.infrastructure.community;

import com.rudkids.rudkids.domain.community.CommunityReader;
import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.community.domain.CommunityType;
import com.rudkids.rudkids.domain.community.exception.CommunityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommunityReaderImpl implements CommunityReader {
    private final CommunityRepository communityRepository;

    @Override
    public Community getMagazine(UUID magazineId) {
        return communityRepository.findById(magazineId)
                .orElseThrow(CommunityNotFoundException::new);
    }

    @Override
    public List<Community> getCommunities(String type) {
        CommunityType communityType = CommunityType.toEnum(type);
        return communityRepository.findByCommunityType(communityType);
    }
}
