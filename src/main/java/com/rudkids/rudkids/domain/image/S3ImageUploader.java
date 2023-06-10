package com.rudkids.rudkids.domain.image;

import org.springframework.web.multipart.MultipartFile;

public interface S3ImageUploader {
    String upload(MultipartFile image, String fileName);
}