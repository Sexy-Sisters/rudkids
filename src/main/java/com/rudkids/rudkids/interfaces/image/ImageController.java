package com.rudkids.rudkids.interfaces.image;

import com.rudkids.rudkids.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
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
