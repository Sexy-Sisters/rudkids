package com.rudkids.rudkids.domain.communityLike.service;

import com.rudkids.rudkids.domain.community.CommunityReader;
import com.rudkids.rudkids.domain.communityLike.CommunityLikeReader;
import com.rudkids.rudkids.domain.communityLike.CommunityLikeStore;
import com.rudkids.rudkids.domain.communityLike.domain.CommunityLike;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityLikeServiceImpl implements CommunityLikeService {
    private final UserReader userReader;
    private final CommunityReader communityReader;
    private final CommunityLikeReader communityLikeReader;
    private final CommunityLikeStore communityLikeStore;

    @Override
    public void like(UUID userId, UUID communityId) {
        var user = userReader.getUser(userId);
        var community = communityReader.get(communityId);

        if (communityLikeReader.hasLike(community, user)) {
            communityLikeStore.delete(community, user);
        }
        else communityLikeStore.store(CommunityLike.create(community, user));
    }
}
