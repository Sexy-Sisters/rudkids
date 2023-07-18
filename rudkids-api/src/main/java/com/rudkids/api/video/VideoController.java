package com.rudkids.api.video;

import com.rudkids.core.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/video")
public class VideoController {
    private final VideoService videoService;

    @GetMapping
    public ResponseEntity getAll(@PageableDefault Pageable pageable) {
        var response = videoService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity get(@PathVariable("name") String name) {
        var response = videoService.get(name);
        return ResponseEntity.ok(response);
    }
}
