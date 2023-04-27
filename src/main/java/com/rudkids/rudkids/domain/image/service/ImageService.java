package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.product.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    ImageInfo.Product upload(UUID userId, MultipartFile frontImage, MultipartFile backImage);
    List<ImageInfo.Main> upload(UUID userId, List<MultipartFile> images);
    void delete(Item item);
    void delete(Product product);
}
