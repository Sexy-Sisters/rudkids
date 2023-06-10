package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.image.ImageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ImageInfo.Main upload(MultipartFile image);
    ImageInfo.All uploads(List<MultipartFile> images);
}
