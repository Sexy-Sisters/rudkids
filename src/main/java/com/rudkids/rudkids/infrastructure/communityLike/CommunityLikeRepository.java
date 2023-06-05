package com.rudkids.rudkids.infrastructure.communityLike;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.communityLike.domain.CommunityLike;
import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, UUID> {
    boolean existsByCommunityAndUser(Community community, User user);
    void deleteByCommunityAndUser(Community community, User user);
}
