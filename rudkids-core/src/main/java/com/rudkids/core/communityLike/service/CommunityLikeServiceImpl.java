package com.rudkids.core.communityLike.service;

import com.rudkids.core.community.domain.CommunityRepository;
import com.rudkids.core.communityLike.domain.CommunityLike;
import com.rudkids.core.communityLike.domain.CommunityLikeRepository;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityLikeServiceImpl implements CommunityLikeService {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final CommunityLikeRepository communityLikeRepository;

    @Override
    public void like(UUID userId, UUID communityId) {
        var user = userRepository.getUser(userId);
        var community = communityRepository.getCommunity(communityId);

        if (communityLikeRepository.hasCommunityLike(community, user)) {
            communityLikeRepository.delete(community, user);
        }
        else communityLikeRepository.save(CommunityLike.create(community, user));
    }
}
