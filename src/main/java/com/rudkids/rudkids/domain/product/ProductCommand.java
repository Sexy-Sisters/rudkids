package com.rudkids.rudkids.domain.product;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public class ProductCommand {

    @Builder
    public record RegisterRequest(
            String title,
            String productBio,
            MultipartFile frontImage,
            MultipartFile backImage
    ) {
    };
}
