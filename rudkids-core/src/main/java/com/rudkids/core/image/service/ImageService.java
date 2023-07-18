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
public class ImageService {
    private final ImageClient imageClient;
    private final FileNameGenerator fileNameGenerator;

    public ImageResponse.Info upload(MultipartFile file) {
        validate(file.getOriginalFilename());
        String fileName = fileNameGenerator.generate(file.getOriginalFilename());
        String uploadUrl = imageClient.upload(file, fileName);
        return new ImageResponse.Info(fileName, uploadUrl);
    }

    public List<ImageResponse.Info> uploads(List<MultipartFile> files) {
        return files.stream()
            .map(this::upload)
            .toList();
    }

    public void delete(String path) {
        validate(path);
        imageClient.delete(path);
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