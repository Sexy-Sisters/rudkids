package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.S3ImageUploader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemImage;
import com.rudkids.rudkids.domain.product.domain.ProductBackImage;
import com.rudkids.rudkids.domain.product.domain.ProductFrontImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final S3ImageUploader s3ImageUploader;

    @Override
    public ImageInfo.Product upload(MultipartFile frontImage, MultipartFile backImage) {
        var fImageInfo = s3ImageUploader.upload(frontImage);
        var bImageInfo = s3ImageUploader.upload(backImage);

        ProductFrontImage fImage = ProductFrontImage.create(fImageInfo.path(), fImageInfo.url());
        ProductBackImage bImage = ProductBackImage.create(bImageInfo.path(), bImageInfo.url());
        return new ImageInfo.Product(fImage, bImage);
    }

    @Override
    public void upload(List<MultipartFile> images, Item item) {
        images.forEach(image -> {
            var imageInfo = s3ImageUploader.upload(image);
            ItemImage itemImage = ItemImage.create(item, imageInfo.path(), imageInfo.url());
            item.addImage(itemImage);
        });
    }

    @Override
    public void delete(Item item) {
        if(!item.hasImages()) {
            item.getImagePaths().forEach(s3ImageUploader::delete);
        }
    }
}
