package com.rudkids.rudkids.domain.image;

import org.springframework.web.multipart.MultipartFile;

public interface S3ImageUploader {

    ImageInfo.Main upload(MultipartFile image);
    void delete(String path);
}