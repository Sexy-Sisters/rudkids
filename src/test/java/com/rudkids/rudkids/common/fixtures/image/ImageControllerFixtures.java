package com.rudkids.rudkids.common.fixtures.image;

import com.rudkids.rudkids.domain.image.ImageInfo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

public class ImageControllerFixtures {
    public static final String IMAGE_DEFAULT_URL = "/api/v1/image";

    private static final String IMAGE_이름 = "image";
    private static final String IMAGES_이름 = "images";
    private static final String IMAGE_FILE_이름 = "test.jpeg";
    private static final String IMAGE_TYPE = "image/jpeg";
    private static final String IMAGE_경로 = "/test/image/test.jpeg";
    private static final String IMAGE_PATH = "path";
    private static final String IMAGE_URL = "url";

    public static MockMultipartFile IMAGE_FILE() {
        try {
            return new MockMultipartFile(IMAGE_이름, IMAGE_FILE_이름, IMAGE_TYPE, new ClassPathResource(IMAGE_경로).getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<MockMultipartFile> IMAGE_FILES() {
        try {
            return List.of(
                new MockMultipartFile(IMAGES_이름, IMAGE_FILE_이름, IMAGE_TYPE, new ClassPathResource(IMAGE_경로).getInputStream()),
                new MockMultipartFile(IMAGES_이름, IMAGE_FILE_이름, IMAGE_TYPE, new ClassPathResource(IMAGE_경로).getInputStream())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageInfo.Main IMAGE_응답() {
        return new ImageInfo.Main(IMAGE_PATH, IMAGE_URL);
    }

    public static ImageInfo.All IMAGE_여러개_응답() {
        return new ImageInfo.All(
            List.of(
                new ImageInfo.Main(IMAGE_PATH, IMAGE_URL),
                new ImageInfo.Main(IMAGE_PATH, IMAGE_URL)
            )
        );
    }
}
