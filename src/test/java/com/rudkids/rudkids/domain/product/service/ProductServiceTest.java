package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.common.fixtures.product.ProductServiceFixtures;
import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductServiceTest extends ProductServiceFixtures {

    @DisplayName("프로덕트 등록")
    @Test
    void registerProduct() {
        ProductCommand.RegisterRequest command = ProductCommand.RegisterRequest.builder()
            .title("Strange Drugstore")
            .productBio("약쟁이가 약팝니다~~~~")
            .build();
        productService.create(command, user.getId());

        Product findProduct = productReader.getProduct(command.title());
        assertAll(
            () -> assertThat(findProduct.getTitle()).isEqualTo("Strange Drugstore"),
            () -> assertThat(findProduct.getProductBio()).isEqualTo("약쟁이가 약팝니다~~~~")
        );
    }

    @DisplayName("프로덕트 리스트 조회")
    @Test
    void findProducts() {
        List<ProductInfo.Main> products = productService.findAll();
        assertThat(products.size()).isEqualTo(4);
    }

    @DisplayName("특정 프로덕트 상세 조회")
    @Test
    void 특정_프로덕트_상세_조회() {
        var productDetailInfo = productService.find(products.get(0).getId());
        assertAll(
            () -> assertThat(productDetailInfo.title()).isEqualTo("프로덕트 No.1"),
            () -> assertThat(productDetailInfo.bio()).isEqualTo("소개드립니다~")
        );
    }

    @DisplayName("프로덕트 종료")
    @Test
    void closeProduct() {
        Product product = products.get(0);
        Product findProduct = productReader.getProduct(product.getId());
        findProduct.close();

        assertAll(
            () -> assertThat(findProduct.getProductStatus()).isEqualTo(ProductStatus.CLOSED),
            () -> assertThat(findProduct.getTitle()).isEqualTo("프로덕트 No.1"),
            () -> assertThat(findProduct.getProductBio()).isEqualTo("소개드립니다~")
        );
    }

    @DisplayName("프로덕트 오픈")
    @Test
    void openProduct() {
        Product product = products.get(0);
        Product findProduct = productReader.getProduct(product.getId());
        findProduct.open();

        assertAll(
            () -> assertThat(findProduct.getProductStatus()).isEqualTo(ProductStatus.OPEN),
            () -> assertThat(findProduct.getTitle()).isEqualTo("프로덕트 No.1"),
            () -> assertThat(findProduct.getProductBio()).isEqualTo("소개드립니다~")
        );
    }
}