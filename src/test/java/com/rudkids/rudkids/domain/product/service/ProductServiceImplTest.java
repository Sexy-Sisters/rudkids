package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.util.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ServiceTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReader productReader;

    @DisplayName("프로덕트 등록")
    @Test
    void registerProduct() {
        ProductCommand.RegisterRequest command = ProductCommand.RegisterRequest.builder()
            .title("알약~~")
            .bio("약쟁이가 약팝니다~~~~")
            .build();
        productService.registerProduct(command);

        Product findProduct = productReader.getProduct(command.getTitle());
        assertAll(
            () -> assertThat(findProduct.getTitle()).isEqualTo("알약~~"),
            () -> assertThat(findProduct.getBio()).isEqualTo("약쟁이가 약팝니다~~~~")
        );
    }
}