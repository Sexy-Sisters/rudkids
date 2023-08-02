package com.rudkids.core.image.dto;

import com.rudkids.core.item.domain.ItemImage;

public class ImageResponse {

    public record Info(String path, String url) {
        public Info(ItemImage image) {
            this(image.getPath(), image.getUrl());
        }
    }
}
