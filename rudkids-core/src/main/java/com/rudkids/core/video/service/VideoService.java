package com.rudkids.core.video.service;

import com.rudkids.core.video.domain.VideoRepository;
import com.rudkids.core.video.dto.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public Page<VideoResponse.Info> getAll(Pageable pageable) {
        return videoRepository.findAll(pageable)
            .map(VideoResponse.Info::new);
    }

    public VideoResponse.Detail get(String name) {
        var video = videoRepository.findByItemName(name);
        return new VideoResponse.Detail(video);
    }
}
