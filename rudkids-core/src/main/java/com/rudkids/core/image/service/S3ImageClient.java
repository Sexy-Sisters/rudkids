package com.rudkids.core.image.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3ImageClient {
    String upload(MultipartFile image, String fileName);
    void delete(String fileName);
}