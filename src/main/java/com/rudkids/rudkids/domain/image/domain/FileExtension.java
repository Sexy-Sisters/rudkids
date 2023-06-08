package com.rudkids.rudkids.domain.image.domain;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum FileExtension {

    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    BMP("bmp");

    private final String value;

    public static boolean isSupport(String extension) {
        return Stream.of(values())
            .anyMatch(it -> it.value.equalsIgnoreCase(extension));
    }
}