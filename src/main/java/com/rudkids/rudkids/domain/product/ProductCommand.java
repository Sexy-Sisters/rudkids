package com.rudkids.rudkids.domain.product;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public class ProductCommand {

    @Builder
    public record CreateRequest(
        String title,
        String productBio,
        MultipartFile frontImage,
        MultipartFile backImage
    ) {
    }

    public record UpdateRequest(String title, String productBio) {
    }
}
