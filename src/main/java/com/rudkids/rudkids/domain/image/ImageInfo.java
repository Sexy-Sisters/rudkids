package com.rudkids.rudkids.domain.image;

import com.rudkids.rudkids.domain.product.domain.ProductBackImage;
import com.rudkids.rudkids.domain.product.domain.ProductFrontImage;

public class ImageInfo {

    public record Main(String path, String url) {
    }

    public record Product(ProductFrontImage frontImage, ProductBackImage backImage) {
    }
}
