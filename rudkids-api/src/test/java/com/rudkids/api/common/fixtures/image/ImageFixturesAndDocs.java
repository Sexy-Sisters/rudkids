package com.rudkids.api.common.fixtures.image;

import com.rudkids.core.image.dto.ImageResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

public class ImageFixturesAndDocs {
    public static final String IMAGE_DEFAULT_URL = "/api/v1/image";
    private static final String IMAGE_이름 = "image";
    private static final String IMAGE_FILE_이름 = "test.jpeg";
    private static final String IMAGE_TYPE = "image/jpeg";
    private static final String IMAGE_경로 = "/image/test.jpeg";
    private static final String IMAGE_PATH = "path";
    private static final String IMAGE_URL = "url";

    public static MockMultipartFile IMAGE_FILE() {
        try {
            return new MockMultipartFile(IMAGE_이름, IMAGE_FILE_이름, IMAGE_TYPE, new ClassPathResource(IMAGE_경로).getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageResponse.Info IMAGE_응답() {
        return new ImageResponse.Info(IMAGE_PATH, IMAGE_URL);
    }
}
