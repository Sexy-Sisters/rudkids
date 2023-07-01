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
    private final S3ImageUploader s3ImageUploader;
    private final FileNameGenerator fileNameGenerator;

    @Override
    public ImageResponse.Info upload(MultipartFile file) {
        validate(file);
        String fileName = fileNameGenerator.generate(file.getOriginalFilename());
        String uploadUrl = s3ImageUploader.upload(file, fileName);
        return new ImageResponse.Info(fileName, uploadUrl);
    }

    @Override
    public List<ImageResponse.Info> uploads(List<MultipartFile> files) {
        return files.stream()
            .map(it ->  {
                validate(it);
                String fileName = fileNameGenerator.generate(it.getOriginalFilename());
                String uploadUrl = s3ImageUploader.upload(it, fileName);
                return new ImageResponse.Info(fileName, uploadUrl);
            })
            .toList();
    }

    private void validate(MultipartFile file) {
        if(isEmptyFileName(file)) {
            throw new FileNameEmptyException();
        }
    }

    private boolean isEmptyFileName(final MultipartFile uploadImageFile) {
        return Objects.requireNonNull(uploadImageFile.getOriginalFilename()).trim().isEmpty();
    }
}
