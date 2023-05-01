package com.rudkids.rudkids.interfaces.image;

import com.rudkids.rudkids.domain.image.service.ImageService;
import com.rudkids.rudkids.interfaces.admin.AuthenticationAdminAuthority;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity uploadProductImage(
        @AuthenticationAdminAuthority
        @RequestPart MultipartFile image
    ) {
        var response = imageService.upload(image);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/list")
    public ResponseEntity uploadItemImage(
        @AuthenticationAdminAuthority
        @RequestPart List<MultipartFile> images
    ) {
        var response = imageService.upload(images);
        return ResponseEntity.ok(response);
    }
}