package com.rudkids.rudkids.domain.image.domain;

import com.rudkids.rudkids.domain.image.exception.UnsupportedFileExtensionException;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum FileExtension {

    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    BMP("bmp");

    private final String value;

    public static String getSupportedExtension(String extension) {
        return Stream.of(values())
            .filter(it -> it.value.equalsIgnoreCase(extension))
            .findAny()
            .orElseThrow(UnsupportedFileExtensionException::new)
            .value;
    }
}