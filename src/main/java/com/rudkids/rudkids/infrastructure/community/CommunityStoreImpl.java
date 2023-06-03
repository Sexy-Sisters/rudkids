package com.rudkids.rudkids.infrastructure.community;

import com.rudkids.rudkids.domain.community.CommunityStore;
import com.rudkids.rudkids.domain.community.domain.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityStoreImpl implements CommunityStore {
    private final CommunityRepository communityRepository;

    @Override
    public void store(Community community) {
        communityRepository.save(community);
    }

    @Override
    public void delete(Community community) {
        communityRepository.delete(community);
    }
}
