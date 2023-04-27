package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public class ProductCommand {

    @Builder
    public record CreateRequest(
        String title,
        String productBio,
        ImageRequest frontImage,
        ImageRequest backImage
    ) {
    }

    public record UpdateRequest(
        String title,
        String productBio,
        ImageRequest frontImage,
        ImageRequest backImage
    ) {
    }
}
