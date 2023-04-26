package com.rudkids.rudkids.infrastructure.image;

import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.service.ImageUploader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemImage;
import com.rudkids.rudkids.domain.product.domain.ProductBackImage;
import com.rudkids.rudkids.domain.product.domain.ProductFrontImage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ImageUploaderImpl implements ImageUploader {

    @Override
    public ImageInfo.Product upload(MultipartFile frontImage, MultipartFile backImage) {
        var fImageInfo = S3ImageUploader.upload(frontImage);
        var bImageInfo = S3ImageUploader.upload(backImage);

        ProductFrontImage fImage = ProductFrontImage.create(fImageInfo.path(), fImageInfo.url());
        ProductBackImage bImage = ProductBackImage.create(bImageInfo.path(), bImageInfo.url());
        return new ImageInfo.Product(fImage, bImage);
    }

    @Override
    public void upload(List<MultipartFile> images, Item item) {
        images.forEach(image -> {
            var imageInfo = S3ImageUploader.upload(image);
            ItemImage itemImage = ItemImage.create(item, imageInfo.path(), imageInfo.url());
            item.addImage(itemImage);
        });
    }
}
