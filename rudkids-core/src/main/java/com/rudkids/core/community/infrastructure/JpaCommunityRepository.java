package com.rudkids.core.community.infrastructure;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.community.domain.CommunityType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaCommunityRepository extends JpaRepository<Community, UUID> {
    List<Community> findByCommunityType(CommunityType type, Pageable pageable);
}