package com.rudkids.core.image.service;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.image.exception.FileNameEmptyException;
import com.rudkids.core.image.infrastructure.FileNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final S3ImageClient s3ImageClient;
    private final FileNameGenerator fileNameGenerator;

    @Override
    public ImageResponse.Info upload(MultipartFile file) {
        validate(file.getOriginalFilename());
        String fileName = fileNameGenerator.generate(file.getOriginalFilename());
        String uploadUrl = s3ImageClient.upload(file, fileName);
        return new ImageResponse.Info(fileName, uploadUrl);
    }

    @Override
    public List<ImageResponse.Info> uploads(List<MultipartFile> files) {
        return files.stream()
            .map(this::upload)
            .toList();
    }

    @Override
    public void delete(String path) {
        validate(path);
        s3ImageClient.delete(path);
    }

    private void validate(String fileName) {
        if(isEmptyFileName(fileName)) {
            throw new FileNameEmptyException();
        }
    }

    private boolean isEmptyFileName(String fileName) {
        return Objects.requireNonNull(fileName).trim().isEmpty();
    }
}
