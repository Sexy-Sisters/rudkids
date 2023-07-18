package com.rudkids.core.video.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VideoRepository {
    void save(Video video);
    void delete(Video video);
    Video findVideoById(UUID videoId);
    Page<Video> findAll(Pageable pageable);
    Video findByItemName(String name);
}
