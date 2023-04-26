package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.common.fixtures.product.ProductServiceFixtures;
import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductServiceTest extends ProductServiceFixtures {

    @DisplayName("프로덕트 등록")
    @Test
    void create() {
        // Given
        ProductCommand.CreateRequest command = new ProductCommand.CreateRequest(
            "Strange Drugstore",
            "약쟁이가 약팝니다~~~~"
        );

        // When
        productService.create(command, user.getId());

        // Then
        Product findProduct = productReader.getProduct(command.title());
        assertAll(
            () -> assertThat(findProduct.getTitle()).isEqualTo("Strange Drugstore"),
            () -> assertThat(findProduct.getProductBio()).isEqualTo("약쟁이가 약팝니다~~~~")
        );
    }

    @DisplayName("프로덕트 리스트 조회")
    @Test
    void findAll() {
        // Given & When
        var products = productService.findAll();

        // Then
        assertThat(products.size()).isEqualTo(4);
    }

    @DisplayName("프로덕트 상세 조회")
    @Test
    void find() {
        // Given
        var product = products.get(0);

        // When
        var productDetailInfo = productService.find(product.getId());

        // Then
        assertAll(
            () -> assertThat(productDetailInfo.title()).isEqualTo("프로덕트 No.1"),
            () -> assertThat(productDetailInfo.bio()).isEqualTo("소개드립니다~")
        );
    }

    @DisplayName("프로덕트 상태 변경 [오픈]")
    @Test
    void open() {
        // Given
        var product = products.get(0);
        var status = ProductStatus.OPEN;
        var productId = product.getId();
        var userId = user.getId();

        // When
        productService.changeStatus(status, productId, userId);

        // Then
        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.OPEN);
    }

    @DisplayName("프로덕트 상태 변경 [클로즈]")
    @Test
    void close() {
        // Given
        var product = products.get(0);
        var status = ProductStatus.CLOSED;
        var productId = product.getId();
        var userId = user.getId();

        // When
        productService.changeStatus(status, productId, userId);

        // Then
        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.CLOSED);
    }
}