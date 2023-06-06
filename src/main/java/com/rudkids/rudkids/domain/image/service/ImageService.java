package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    ImageInfo.Main upload(MultipartFile image);
    ImageInfo.All uploads(List<MultipartFile> images);
    void delete(Item item);
    void delete(Product product);
    void delete(User user);
    void delete(Community community);
}
