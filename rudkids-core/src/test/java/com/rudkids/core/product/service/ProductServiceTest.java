package com.rudkids.core.product.service;

import com.rudkids.core.common.fixtures.ProductServiceFixtures;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductStatus;
import com.rudkids.core.product.dto.ProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductServiceTest extends ProductServiceFixtures {

    @DisplayName("[프로덕트-생성]")
    @Test
    void 프로덕트를_생성한다() {
        // Given
        ProductRequest.Create command = new ProductRequest.Create(
            "Strange Drugstore",
            "약쟁이가 약팝니다~~~~",
            new ImageRequest.Create("image", "image.jpg"),
            new ImageRequest.Create("image", "image.jpg"),
            List.of(
                new ImageRequest.Create("image", "image.jpg")
            ),
            "TOY"
        );

        // When
        UUID productId = productService.create(command);

        // Then
        Product findProduct = productRepository.get(productId);
        assertAll(
            () -> assertThat(findProduct.getTitle()).isEqualTo("Strange Drugstore"),
            () -> assertThat(findProduct.getProductBio()).isEqualTo("약쟁이가 약팝니다~~~~")
        );
    }

    @DisplayName("[프로덕트-전체조회]")
    @Test
    void 프로덕트를_전체조회한다() {
        // Given & When
        int page = 0;
        int size = 4;
        Pageable pageable = PageRequest.of(page, size);
        var products = productService.getAll(pageable);

        // Then
        assertThat(products.getSize()).isEqualTo(4);
    }

    @DisplayName("[프로덕트-상세조회]")
    @Test
    void 프로덕트를_상세조회한다() {
        // Given
        var product = products.get(0);

        // When
        int page = 0;
        int size = 6;
        Pageable pageable = PageRequest.of(page, size);
        var productDetailInfo = productService.get(product.getId(), pageable);

        // Then
        assertAll(
            () -> assertThat(productDetailInfo.title()).isEqualTo("프로덕트 No.1"),
            () -> assertThat(productDetailInfo.bio()).isEqualTo("소개드립니다~")
        );
    }

    @DisplayName("[프로덕트-카테도리별-조회]")
    @Test
    void 카테고리별로_프로덕트들을_조회한다() {
        //given, when
        final String category = "TOY";
        var productInfo = productService.getByCategory(category);

        //then
        assertThat(productInfo).hasSize(4);
    }

    @DisplayName("[프로덕트-상태변경-오픈]")
    @Test
    void 프로덕트의_상태를_오픈으로_변경한다() {
        // Given
        var product = products.get(0);
        var status = "OPEN";
        var productId = product.getId();

        // When
        productService.changeStatus(productId, status);

        // Then
        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.OPEN);
    }

    @DisplayName("[프로덕트-상태변경-클로즈]")
    @Test
    void 프로덕트의_상태를_클로즈로_변경한다() {
        // Given
        var product = products.get(0);
        var status = "CLOSED";
        var productId = product.getId();

        // When
        productService.changeStatus(productId, status);

        // Then
        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.CLOSED);
    }
}