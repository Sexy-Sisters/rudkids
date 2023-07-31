package com.rudkids.core.image.infrastructure;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.rudkids.core.image.exception.FileUploadFailException;
import com.rudkids.core.image.service.ImageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3ImageClient implements ImageClient {
    private final AmazonS3 amazonS3;

    @Value("${application.bucket.name}")
    private String bucket;

    @Override
    public String upload(MultipartFile file, String fileName) {
        putImageFileToS3(file, fileName);
        return createUploadUrl(fileName);
    }

    private void putImageFileToS3(MultipartFile file, String fileName) {
        try {
            amazonS3.putObject(new PutObjectRequest(
                bucket,
                fileName,
                file.getInputStream(),
                generateObjectMetadata(file))
                .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FileUploadFailException();
        }
    }

    private ObjectMetadata generateObjectMetadata(MultipartFile image) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType(image.getContentType());
        return objectMetadata;
    }

    private String createUploadUrl(String fileName) {
        return String.valueOf(amazonS3.getUrl(bucket, fileName));
    }

    @Override
    public void delete(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }
}
