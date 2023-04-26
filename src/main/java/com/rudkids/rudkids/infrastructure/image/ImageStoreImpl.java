package com.rudkids.rudkids.infrastructure.image;

import com.rudkids.rudkids.domain.image.ImageStore;
import com.rudkids.rudkids.domain.image.domain.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageStoreImpl implements ImageStore {
    private final ImageRepository imageRepository;

    @Override
    public void store(Image image) {
        imageRepository.save(image);
    }
}
