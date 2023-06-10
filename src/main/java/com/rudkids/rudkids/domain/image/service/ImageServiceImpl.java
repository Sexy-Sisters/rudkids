package com.rudkids.rudkids.domain.image.service;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.image.ImageInfo;
import com.rudkids.rudkids.domain.image.S3ImageUploader;
import com.rudkids.rudkids.domain.image.exception.FileNameEmptyException;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.infrastructure.image.FileNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final S3ImageUploader s3ImageUploader;
    private final FileNameGenerator fileNameGenerator;

    @Override
    public ImageInfo.Main upload(MultipartFile file) {
        validate(file);
        String fileName = fileNameGenerator.generate(file.getOriginalFilename());
        String uploadUrl = s3ImageUploader.upload(file, fileName);
        return new ImageInfo.Main(fileName, uploadUrl);
    }

    @Override
    public ImageInfo.All uploads(List<MultipartFile> files) {
        return files.stream()
            .map(it ->  {
                validate(it);
                String fileName = fileNameGenerator.generate(it.getOriginalFilename());
                String uploadUrl = s3ImageUploader.upload(it, fileName);
                return new ImageInfo.Main(fileName, uploadUrl);
            })
            .collect(collectingAndThen(toList(), ImageInfo.All::new));
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
