package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.image.ImageInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
    ImageInfo.Product upload(MultipartFile frontImage, MultipartFile backImage);
}
