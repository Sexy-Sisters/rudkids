package com.rudkids.rudkids.interfaces.image;

import com.rudkids.rudkids.domain.image.service.ImageService;
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

    @PostMapping("/product")
    public ResponseEntity uploadProductImage(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestPart MultipartFile frontImage,
        @RequestPart MultipartFile backImage
    ) {
        var response = imageService.upload(loginUser.id(), frontImage, backImage);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/item")
    public ResponseEntity uploadItemImage(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestPart List<MultipartFile> images
    ) {
        var response = imageService.upload(loginUser.id(), images);
        return ResponseEntity.ok(response);
    }
}
