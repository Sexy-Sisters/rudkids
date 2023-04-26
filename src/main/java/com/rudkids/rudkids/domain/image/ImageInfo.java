package com.rudkids.rudkids.domain.image;

import com.rudkids.rudkids.domain.product.domain.ProductImage;

public class ImageInfo {

    public record Main(String path, String url) {
    }

    public record Product(ProductImage frontImage, ProductImage backImage) {
    }
}
