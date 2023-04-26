package com.rudkids.rudkids.infrastructure.image;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rudkids.rudkids.domain.image.ImageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 2023.4.26 nswon
 * aws 계정 받고 나서 주석 해제
 */
public class S3ImageUploader {
//    @Value("${cloud.aws.s3.bucket}")
    private static String bucket;
//    private final AmazonS3 amazonS3;
//    private final AmazonS3Client amazonS3Client;

    public static ImageInfo.Main upload(MultipartFile image) {
        String path = UUID.randomUUID() + "_" + image.getOriginalFilename();
        var objectMetadata = generateObjectMetadata(image);
        putS3(bucket, path, image, objectMetadata);
//        String url = amazonS3Client.getUrl(bucket, path);
        String url = "";
        return new ImageInfo.Main(path, url);
    }

    private static ObjectMetadata generateObjectMetadata(MultipartFile image) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType(image.getContentType());
        return objectMetadata;
    }

    private static void putS3(String bucket, String path, MultipartFile image, ObjectMetadata objectMetadata) {
//        try {
//            amazonS3.putObject(new PutObjectRequest(bucket, path, image.getInputStream(), objectMetadata)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
