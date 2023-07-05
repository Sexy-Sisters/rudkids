package com.rudkids.core.image.service;

import com.rudkids.core.image.dto.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ImageResponse.Info upload(MultipartFile image);
    List<ImageResponse.Info> uploads(List<MultipartFile> images);
    void delete(String path);
}