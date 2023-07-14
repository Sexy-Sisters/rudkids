package com.rudkids.core.common.fixtures.image;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@ServiceTest
public class ImageServiceFixtures {

    @Autowired
    protected ImageService imageService;

    protected MultipartFile file() {
        try {
            return new MockMultipartFile(
                "image",
                "test.jpeg",
                "image/jpeg",
                new ClassPathResource("/image/test.jpeg").getInputStream()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
