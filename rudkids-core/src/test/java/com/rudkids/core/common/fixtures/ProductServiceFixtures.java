package com.rudkids.core.common.fixtures;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.product.domain.*;
import com.rudkids.core.product.service.ProductService;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class ProductServiceFixtures {
    @Autowired
    protected ProductService productService;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ItemRepository itemRepository;

    @Autowired
    protected JpaUserRepository userRepository;

    protected List<Product> products;
    protected Item item;
    protected User user;

    @BeforeEach
    void inputData() {
        ProfileImage profileImage = ProfileImage.create("path", "url");
        user = User.builder()
            .name(UserName.create("이규진"))
            .age(19)
            .email("leekuin14@gmail.com")
            .gender("MAIL")
            .phoneNumber(PhoneNumber.create("01029401509"))
            .profileImage(profileImage)
            .socialType(SocialType.GOOGLE)
            .build();
        user.changeAuthorityAdmin();
        userRepository.save(user);

        products = List.of(
            Product.builder()
                .title(Title.create("프로덕트 No.1"))
                .productBio(ProductBio.create("소개드립니다~"))
                .frontImage(ProductFrontImage.create("path", "url"))
                .backImage(ProductBackImage.create("path", "url"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.2"))
                .productBio(ProductBio.create("소개드립니다~"))
                .frontImage(ProductFrontImage.create("path", "url"))
                .backImage(ProductBackImage.create("path", "url"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.3"))
                .productBio(ProductBio.create("소개드립니다~"))
                .frontImage(ProductFrontImage.create("path", "url"))
                .backImage(ProductBackImage.create("path", "url"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.4"))
                .productBio(ProductBio.create("소개드립니다~"))
                .frontImage(ProductFrontImage.create("path", "url"))
                .backImage(ProductBackImage.create("path", "url"))
                .build()
        );
        products.forEach(productRepository::save);

        item = Item.builder()
            .name(Name.create("No.3", "남바쓰리"))
            .price(Price.create(2_990))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.NORMAL)
            .build();
        itemRepository.save(item);
        item.setProduct(products.get(0));
    }
}
