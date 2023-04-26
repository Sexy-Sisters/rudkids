package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class ProductRequest {

    @Getter
    @Setter
    public static class Register {
        private String title;
        private String productBio;
        private MultipartFile frontImage;
        private MultipartFile backImage;

        public Register(String title, String productBio, MultipartFile frontImage, MultipartFile backImage) {
            this.title = title;
            this.productBio = productBio;
            this.frontImage = frontImage;
            this.backImage = backImage;
        }
    }

    public record ChangeStatus(ProductStatus productStatus) {
    }
}
