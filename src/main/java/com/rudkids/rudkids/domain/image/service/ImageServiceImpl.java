package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.S3ImageUploader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final S3ImageUploader s3ImageUploader;

    @Override
    public ImageInfo.Main upload(MultipartFile image) {
        return s3ImageUploader.upload(image);
    }

    @Override
    public ImageInfo.All uploads(List<MultipartFile> images) {
        return images.stream()
            .map(s3ImageUploader::upload)
            .collect(collectingAndThen(toList(), ImageInfo.All::new));
    }

    @Override
    public void delete(Item item) {
        if(!item.hasImages()) {
            item.getImagePaths().forEach(s3ImageUploader::delete);
        }
    }

    @Override
    public void delete(Product product) {
        if(!product.hasImage()) {
            s3ImageUploader.delete(product.getFrontImagePath());
            s3ImageUploader.delete(product.getBackImagePath());
        }
    }

    @Override
    public void delete(User user) {
        if(!user.isCustomProfileImage()) {
            s3ImageUploader.delete(user.getProfileImagePath());
        }
    }

    @Override
    public void delete(Community community) {
        if(!community.hasImage()) {
            s3ImageUploader.delete(community.getPath());
        }
    }
}
