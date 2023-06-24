package com.rudkids.core.community.infrastructure;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.community.domain.CommunityRepository;
import com.rudkids.core.community.domain.CommunityType;
import com.rudkids.core.community.exception.CommunityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepository {
    private final JpaCommunityRepository communityRepository;

    @Override
    public void save(Community community) {
        communityRepository.save(community);
    }

    @Override
    public List<Community> getCommunities(String type, Pageable pageable) {
        CommunityType communityType = CommunityType.toEnum(type);
        return communityRepository.findByCommunityType(communityType, pageable);
    }

    @Override
    public List<String> getImageFileNames() {
        return communityRepository.findPathsByDeletedTrue();
    }

    @Override
    public Community getCommunity(UUID id) {
        return communityRepository.findById(id)
            .orElseThrow(CommunityNotFoundException::new);
    }

    @Override
    public void delete(Community community) {
        communityRepository.delete(community);
    }
}
