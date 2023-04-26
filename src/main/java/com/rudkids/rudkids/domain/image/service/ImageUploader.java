package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.item.domain.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploader {
    ImageInfo.Product upload(MultipartFile frontImage, MultipartFile backImage);
    void upload(List<MultipartFile> images, Item item);
}
