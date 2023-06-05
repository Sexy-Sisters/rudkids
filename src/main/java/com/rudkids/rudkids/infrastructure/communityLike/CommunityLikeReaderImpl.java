package com.rudkids.rudkids.infrastructure.communityLike;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.communityLike.CommunityLikeReader;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunityLikeReaderImpl implements CommunityLikeReader {
    private final CommunityLikeRepository communityLikeRepository;

    @Override
    public boolean hasLike(Community community, User user) {
        return communityLikeRepository.existsByCommunityAndUser(community, user);
    }
}
