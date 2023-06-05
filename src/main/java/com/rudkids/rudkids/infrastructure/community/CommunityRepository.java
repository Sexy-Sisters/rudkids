package com.rudkids.rudkids.infrastructure.community;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.community.domain.CommunityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommunityRepository extends JpaRepository<Community, UUID> {
    List<Community> findByCommunityType(CommunityType type);
}