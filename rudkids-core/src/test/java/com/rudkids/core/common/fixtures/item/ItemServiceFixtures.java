package com.rudkids.core.common.fixtures.item;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.item.dto.ItemRequest;
import com.rudkids.core.item.service.ItemService;
import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductBio;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.product.domain.Title;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    protected static ItemRequest.Create ITEM_등록_요청;

    protected ItemRequest.Update 아이템_수정_요청() {
        return ItemRequest.Update.builder()
            .enName("item")
            .koName("아이템")
            .itemBio("소개글")
            .price(1000)
            .quantity(100)
            .limitType(LimitType.LIMITED)
            .images(List.of(new ImageRequest.Create("path", "url"), new ImageRequest.Create("path", "url")))
            .build();
    }

    private static final ItemRequest.CreateItemOptionGroup itemOptionGroup_사이즈 = ItemRequest.CreateItemOptionGroup.builder()
        .itemOptionGroupName("사이즈")
        .itemOptionList(List.of(
            ItemRequest.CreateItemOption.builder()
                .itemOptionName("S")
                .itemOptionPrice(0)
                .build(),
            ItemRequest.CreateItemOption.builder()
                .itemOptionName("M")
                .itemOptionPrice(0)
                .build(),
            ItemRequest.CreateItemOption.builder()
                .itemOptionName("L")
                .itemOptionPrice(1000)
                .build()
        )).build();

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

        item = Item.builder()
            .name(Name.create("No.1", "남바완"))
            .price(Price.create(2_990))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.LIMITED)
            .video(ItemVideo.create("path", "url", "videoUrl"))
            .build();
        ItemImage image = ItemImage.create(item, "path", "url", 1);
        ItemImage image2 = ItemImage.create(item, "path", "url", 2);
        item.addImage(image);
        item.addImage(image2);
        itemRepository.save(item);

        ITEM_등록_요청 = ItemRequest.Create.builder()
            .enName("Red Pill")
            .koName("레드필")
            .price(1_000_000)
            .quantity(1)
            .itemBio("소개글입니다~")
            .limitType(LimitType.LIMITED)
            .images(List.of(
                    new ItemRequest.CreateImage(
                        new ImageRequest.Create("path", "url"), 1),

                    new ItemRequest.CreateImage(
                        new ImageRequest.Create("path", "url"), 1)
                )
            )
            .itemOptionGroupList(List.of(itemOptionGroup_사이즈))
            .videoImage(new ImageRequest.Create("path", "url"))
            .videoUrl("videoUrl")
            .build();
    }
}
