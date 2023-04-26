package com.rudkids.rudkids.infrastructure.image;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rudkids.rudkids.domain.image.ImageStore;
import com.rudkids.rudkids.domain.image.domain.Image;
import com.rudkids.rudkids.domain.image.service.ImageUploader;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageUploaderImpl implements ImageUploader {
    private final ProductReader productReader;
    private final ImageStore imageStore;

//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;
//    private final AmazonS3 amazonS3;
//    private final AmazonS3Client amazonS3Client;

    @Override
    public void upload(List<MultipartFile> images, UUID productId) {
        var product = productReader.getProduct(productId);
        images.forEach(image -> generateImage(image, product));
    }

    private void generateImage(MultipartFile image, Product product) {
        String path = UUID.randomUUID() + "_" + image.getOriginalFilename();
        ObjectMetadata objectMetadata = generateObjectMetadata(image);
        putS3(path, image, objectMetadata);

//        String url = String.valueOf(amazonS3Client.getUrl(bucket, path));
        String url = "";
        var generateImage = Image.builder()
                .product(product)
                .path(path)
                .url(url)
                .build();
        product.addImage(generateImage);
        imageStore.store(generateImage);
    }

    private ObjectMetadata generateObjectMetadata(MultipartFile image) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType(image.getContentType());
        return objectMetadata;
    }

    private void putS3(String path, MultipartFile image, ObjectMetadata objectMetadata) {
//        try {
//            amazonS3.putObject(new PutObjectRequest(bucket, path, image.getInputStream(), objectMetadata)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
