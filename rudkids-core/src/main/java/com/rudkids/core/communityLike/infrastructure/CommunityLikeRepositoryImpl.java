package com.rudkids.core.communityLike.infrastructure;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.communityLike.domain.CommunityLike;
import com.rudkids.core.communityLike.domain.CommunityLikeRepository;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityLikeRepositoryImpl implements CommunityLikeRepository {
    private final JpaCommunityLikeRepository communityLikeRepository;

    @Override
    public void save(CommunityLike communityLike) {
        communityLikeRepository.save(communityLike);
    }

    @Override
    public void delete(Community community, User user) {
        communityLikeRepository.deleteByCommunityAndUser(community, user);
    }

    @Override
    public boolean hasCommunityLike(Community community, User user) {
        return communityLikeRepository.existsByCommunityAndUser(community, user);
    }
}
