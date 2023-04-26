package com.rudkids.rudkids.interfaces.image;

import com.rudkids.rudkids.domain.image.service.ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageUploader imageUploader;

    @PostMapping("/{id}")
    public void addProductImage(
            @RequestPart List<MultipartFile> images,
            @PathVariable("id") UUID productId) {
        imageUploader.upload(images, productId);
    }
}
