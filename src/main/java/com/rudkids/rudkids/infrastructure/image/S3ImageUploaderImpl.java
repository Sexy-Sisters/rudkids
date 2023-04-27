package com.rudkids.rudkids.infrastructure.image;

import com.amazonaws.services.s3.model.*;
import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.S3ImageUploader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 2023.4.26 nswon
 * aws 계정 받고 나서 주석 해제
 */
@Component
public class S3ImageUploaderImpl implements S3ImageUploader {
//    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
//    private final AmazonS3 amazonS3;
//    private final AmazonS3Client amazonS3Client;

    @Override
    public ImageInfo.Main upload(MultipartFile image) {
        String path = UUID.randomUUID() + "_" + image.getOriginalFilename();
        var objectMetadata = generateObjectMetadata(image);
        putS3(bucket, path, image, objectMetadata);
//        String url = amazonS3Client.getUrl(bucket, path);
        String url = "";
        return new ImageInfo.Main(path, url);
    }

    private ObjectMetadata generateObjectMetadata(MultipartFile image) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType(image.getContentType());
        return objectMetadata;
    }

    private void putS3(String bucket, String path, MultipartFile image, ObjectMetadata objectMetadata) {
//        try {
//            amazonS3.putObject(new PutObjectRequest(bucket, path, image.getInputStream(), objectMetadata)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void delete(String path) {
//        amazonS3.deleteObject(new DeleteObjectRequest(bucket, path));
    }
}
