package com.rudkids.core.product.service;

import com.rudkids.core.common.fixtures.product.ProductServiceFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductServiceTest extends ProductServiceFixtures {

    @Nested
    @DisplayName("프로덕트들을 조회한다")
    class getAll {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            int page = 0;
            int size = 4;
            Pageable pageable = PageRequest.of(page, size);

            //when
            var products = productService.getAll(pageable);

            //then
            assertThat(products.getSize()).isEqualTo(4);
        }
    }

    @Nested
    @DisplayName("프로덕트를 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var product = products.get(0);

            //when
            int page = 0;
            int size = 6;
            Pageable pageable = PageRequest.of(page, size);
            var productDetailInfo = productService.get(product.getId(), pageable);

            //then
            assertAll(
                () -> assertThat(productDetailInfo.title()).isEqualTo("프로덕트 No.1"),
                () -> assertThat(productDetailInfo.bio()).isEqualTo("소개드립니다~")
            );
        }
    }
}