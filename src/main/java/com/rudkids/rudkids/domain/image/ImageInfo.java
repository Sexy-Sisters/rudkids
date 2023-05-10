package com.rudkids.rudkids.domain.image;

import java.util.List;

public class ImageInfo {

    public record Main(String path, String url) {
    }

    public record All(List<ImageInfo.Main> images) {
    }
}
