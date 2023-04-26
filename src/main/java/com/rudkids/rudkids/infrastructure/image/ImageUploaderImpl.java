package com.rudkids.rudkids.infrastructure.image;

import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.service.ImageUploader;
import com.rudkids.rudkids.domain.product.domain.ProductImage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUploaderImpl implements ImageUploader {

    @Override
    public ImageInfo.Product upload(MultipartFile frontImage, MultipartFile backImage) {
        var fImageInfo = S3ImageUploader.upload(frontImage);
        var bImageInfo = S3ImageUploader.upload(backImage);

        ProductImage fImage = ProductImage.create(fImageInfo.path(), fImageInfo.url());
        ProductImage bImage = ProductImage.create(bImageInfo.path(), bImageInfo.url());
        return new ImageInfo.Product(fImage, bImage);
    }
}
