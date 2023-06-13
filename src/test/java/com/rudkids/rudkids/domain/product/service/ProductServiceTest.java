package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.common.fixtures.product.ProductServiceFixtures;
import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductServiceTest extends ProductServiceFixtures {

    @DisplayName("[프로덕트-생성]")
    @Test
    void 프로덕트를_생성한다() {
        // Given
        ProductCommand.CreateRequest command = new ProductCommand.CreateRequest(
            "Strange Drugstore",
            "약쟁이가 약팝니다~~~~",
            new ImageRequest("image", "image.jpg"),
            new ImageRequest("image", "image.jpg"),
            List.of(
                new ImageRequest("image", "image.jpg")
            )
        );

        // When
        productService.create(command);

        // Then
        Product findProduct = productReader.getProduct(command.title());
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
        var products = productService.findAll(pageable);

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
        var productDetailInfo = productService.find(product.getId(), pageable);

        // Then
        assertAll(
            () -> assertThat(productDetailInfo.title()).isEqualTo("프로덕트 No.1"),
            () -> assertThat(productDetailInfo.bio()).isEqualTo("소개드립니다~")
        );
    }

    @DisplayName("[프로덕트-검색]")
    @Test
    void 제목으로_프로덕트를_검색한다() {
        //given, when
        final String title = "프로덕트";
        var productInfo = productService.search(title);

        //then
        assertThat(productInfo).hasSize(4);
    }

    @DisplayName("[프로덕트-상태변경-오픈]")
    @Test
    void 프로덕트의_상태를_오픈으로_변경한다() {
        // Given
        var product = products.get(0);
        var status = ProductStatus.OPEN;
        var productId = product.getId();

        // When
        productService.changeStatus(status, productId);

        // Then
        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.OPEN);
    }

    @DisplayName("[프로덕트-상태변경-클로즈]")
    @Test
    void 프로덕트의_상태를_클로즈로_변경한다() {
        // Given
        var product = products.get(0);
        var status = ProductStatus.CLOSED;
        var productId = product.getId();
        var userId = user.getId();

        // When
        productService.changeStatus(status, productId);

        // Then
        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.CLOSED);
    }
}