package com.rudkids.core.video.infrastructure;

import com.rudkids.core.video.domain.Video;
import com.rudkids.core.video.domain.VideoRepository;
import com.rudkids.core.video.exception.VideoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VideoRepositoryImpl implements VideoRepository {
    private final JpaVideoRepository videoRepository;

    @Override
    public void save(Video video) {
        videoRepository.save(video);
    }

    @Override
    public void delete(Video video) {
        videoRepository.delete(video);
    }

    @Override
    public Video findVideoById(UUID videoId) {
        return videoRepository.findById(videoId)
            .orElseThrow(VideoNotFoundException::new);
    }

    @Override
    public Page<Video> findAll(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    @Override
    public Video findByItemName(String name) {
        return videoRepository.findByItemName(name)
            .orElseThrow(VideoNotFoundException::new);
    }
}
