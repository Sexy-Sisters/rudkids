package com.rudkids.core.image.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3ImageUploader {
    String upload(MultipartFile image, String fileName);
}