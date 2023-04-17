package com.rudkids.rudkids.common.fixtures.product;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductBio;
import com.rudkids.rudkids.domain.product.domain.Title;
import com.rudkids.rudkids.domain.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class ProductServiceFixtures {

    @Autowired
    protected ProductService productService;

    @Autowired
    protected ProductReader productReader;

    @Autowired
    protected ProductStore productStore;

    @Autowired
    protected ItemStore itemStore;

    protected List<Product> products;
    protected Item item;

    @BeforeEach
    void inputData() {
        products = List.of(
            Product.builder()
                .title(Title.create("프로덕트 No.1"))
                .productBio(ProductBio.create("소개드립니다~"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.2"))
                .productBio(ProductBio.create("소개드립니다~"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.3"))
                .productBio(ProductBio.create("소개드립니다~"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.4"))
                .productBio(ProductBio.create("소개드립니다~"))
                .build()
        );
        products.forEach(productStore::store);


        item = Item.builder()
            .name(Name.create("No.3"))
            .price(Price.create(2_990))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.NORMAL)
            .build();
        itemStore.store(item);
        item.changeProduct(products.get(0));
    }
}
