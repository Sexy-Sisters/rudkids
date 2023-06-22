package com.rudkids.core.image.domain;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum FileExtension {

    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    BMP("bmp");

    private final String value;

    public static boolean isSupport(final String extension) {
        return Stream.of(values())
            .anyMatch(it -> it.isSameExtension(extension));
    }

    private boolean isSameExtension(String extension) {
        return this.value.equalsIgnoreCase(extension);
    }
}