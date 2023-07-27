package com.rudkids.core.common.fixtures.item;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.item.service.ItemService;
import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductBio;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.product.domain.Title;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class ItemServiceFixtures {

    @Autowired
    protected ItemService itemService;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ItemRepository itemRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected JpaUserRepository jpaUserRepository;

    protected static User user;
    protected static Product product;
    protected static Item item;

    @BeforeEach
    void inputData() {
        user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        user.changeAuthorityAdmin();
        jpaUserRepository.save(user);

        product = Product.builder()
            .title(Title.create("Strange Drugstore"))
            .productBio(ProductBio.create("약국입니다~"))
            .build();
        productRepository.save(product);

        var grayImage = GrayImage.create("path", "url");
        item = Item.builder()
            .name(Name.create("No.1", "남바완"))
            .price(Price.create(2_990))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.LIMITED)
            .grayImage(grayImage)
            .build();
        ItemImage image = ItemImage.create(item, "path", "url", 1);
        ItemImage image2 = ItemImage.create(item, "path", "url", 2);
        item.addImage(image);
        item.addImage(image2);
        item.setProduct(product);
        itemRepository.save(item);
    }
}
