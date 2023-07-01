package com.rudkids.core.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3ImageClient {
    String upload(MultipartFile image, String fileName);
    List<String> getObjectKeys();
    void delete(String fileName);
}