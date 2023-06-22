package com.rudkids.api.image;

import com.rudkids.core.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity upload(@RequestPart MultipartFile image) {
        var response = imageService.upload(image);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/list")
    public ResponseEntity uploads(@RequestPart List<MultipartFile> images) {
        var response = imageService.uploads(images);
        return ResponseEntity.ok(response);
    }
}
