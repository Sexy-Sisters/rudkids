package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.S3ImageUploader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final UserReader userReader;
    private final S3ImageUploader s3ImageUploader;

    @Override
    public ImageInfo.Product upload(UUID userId, MultipartFile frontImage, MultipartFile backImage) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();

        var fImageInfo = s3ImageUploader.upload(frontImage);
        var bImageInfo = s3ImageUploader.upload(backImage);
        return new ImageInfo.Product(fImageInfo, bImageInfo);
    }

    @Override
    public List<ImageInfo.Main> upload(UUID userId, List<MultipartFile> images) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();

        return images.stream()
            .map(s3ImageUploader::upload)
            .toList();
    }

    @Override
    public void delete(Item item) {
        if(!item.hasImages()) {
            item.getImagePaths().forEach(s3ImageUploader::delete);
        }
    }
}
