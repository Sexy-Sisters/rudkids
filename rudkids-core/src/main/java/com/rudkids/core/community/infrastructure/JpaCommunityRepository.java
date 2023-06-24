package com.rudkids.core.community.infrastructure;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.community.domain.CommunityType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaCommunityRepository extends JpaRepository<Community, UUID> {
    List<Community> findByCommunityType(CommunityType type, Pageable pageable);

    @Query("SELECT c.communityImage.path FROM Community c WHERE c.communityImage.deleted is true")
    List<String> findPathsByDeletedTrue();
}