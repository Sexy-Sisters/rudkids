package com.rudkids.rudkids.infrastructure.image;

import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.service.ImageUploader;
import com.rudkids.rudkids.domain.product.domain.ProductBackImage;
import com.rudkids.rudkids.domain.product.domain.ProductFrontImage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
}
