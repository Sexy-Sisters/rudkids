package com.rudkids.rudkids.domain.product.service;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.Bio;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.domain.product.domain.Title;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private ProductStore productStore;

    private List<Product> products;

    @BeforeEach
    void inputData() {
        products = List.of(
            Product.builder()
                .title(Title.create("프로덕트 No.1"))
                .bio(Bio.create("소개드립니다~"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.2"))
                .bio(Bio.create("소개드립니다~"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.3"))
                .bio(Bio.create("소개드립니다~"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.4"))
                .bio(Bio.create("소개드립니다~"))
                .build()
        );

        products.forEach(productStore::store);
    }

    @DisplayName("프로덕트 등록")
    @Test
    void registerProduct() {
        ProductCommand.RegisterRequest command = ProductCommand.RegisterRequest.builder()
            .title("Strange Drugstore")
            .bio("약쟁이가 약팝니다~~~~")
            .build();
        productService.registerProduct(command);

        Product findProduct = productReader.getProduct(command.getTitle());
<<<<<<< HEAD
        assertThat(findProduct.getTitle()).isEqualTo("Strange Drugstore");
        assertThat(findProduct.getBio()).isEqualTo("약쟁이가 약팝니다~~~~");
=======
        assertAll(
            () -> assertThat(findProduct.getTitle()).isEqualTo("알약~~"),
            () -> assertThat(findProduct.getBio()).isEqualTo("약쟁이가 약팝니다~~~~")
        );
    }

    @DisplayName("프로덕트 리스트 조회")
    @Test
    void findProducts() {
        List<ProductInfo.Main> products = productService.findProduct();
        assertThat(products.size()).isEqualTo(4);
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
            () -> assertThat(findProduct.getBio()).isEqualTo("소개드립니다~")
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
            () -> assertThat(findProduct.getBio()).isEqualTo("소개드립니다~")
        );
>>>>>>> develop
    }
}