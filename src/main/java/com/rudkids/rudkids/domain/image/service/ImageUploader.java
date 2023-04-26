package com.rudkids.rudkids.domain.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageUploader {
    void upload(List<MultipartFile> images, UUID productId);
}
