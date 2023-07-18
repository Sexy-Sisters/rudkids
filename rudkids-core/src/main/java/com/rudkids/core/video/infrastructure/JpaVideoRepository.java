package com.rudkids.core.video.infrastructure;

import com.rudkids.core.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaVideoRepository extends JpaRepository<Video, UUID> {

    Optional<Video> findByItemName(String itemName);
}
