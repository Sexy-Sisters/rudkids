package com.rudkids.rudkids.infrastructure.communityLike;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.communityLike.CommunityLikeStore;
import com.rudkids.rudkids.domain.communityLike.domain.CommunityLike;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityLikeStoreImpl implements CommunityLikeStore {
    private final CommunityLikeRepository communityLikeRepository;

    @Override
    public void store(CommunityLike communityLike) {
        communityLikeRepository.save(communityLike);
    }

    @Override
    public void delete(Community community, User user) {
        communityLikeRepository.deleteByCommunityAndUser(community, user);
    }
}
