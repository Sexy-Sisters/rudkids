package com.rudkids.core.communityLike.infrastructure;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.communityLike.domain.CommunityLike;
import com.rudkids.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCommunityLikeRepository extends JpaRepository<CommunityLike, UUID> {
    boolean existsByCommunityAndUser(Community community, User user);
    void deleteByCommunityAndUser(Community community, User user);
}
