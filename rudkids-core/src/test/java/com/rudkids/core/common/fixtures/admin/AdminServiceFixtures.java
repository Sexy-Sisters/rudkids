package com.rudkids.core.common.fixtures.admin;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.service.AdminService;
import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.product.domain.*;
import com.rudkids.core.product.service.ProductService;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class AdminServiceFixtures {

    @Autowired
    protected AdminService adminService;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ProductService productService;

    @Autowired
    protected ItemRepository itemRepository;

    @Autowired
    protected JpaUserRepository jpaUserRepository;

    protected static User user;
    protected static Product product;
    protected static Item item;

    protected static AdminRequest.CreateItem ITEM_등록_요청;

    protected AdminRequest.UpdateItem 아이템_수정_요청() {
        return AdminRequest.UpdateItem.builder()
            .enName("새로운 item")
            .koName("아이템")
            .itemBio("소개글")
            .price(1000)
            .quantity(100)
            .limitType(LimitType.LIMITED)
            .images(List.of(
                new AdminRequest.Image("path", "url", 1),
                new AdminRequest.Image("path", "url", 1)
            ))
            .itemOptionGroupInfoList(List.of(itemOptionGroup_사이즈))
            .grayImage(new ImageRequest.Create("path", "url"))
            .build();
    }

    private static final AdminRequest.ItemOptionGroup itemOptionGroup_사이즈 = AdminRequest.ItemOptionGroup.builder()
        .itemOptionGroupName("사이즈")
        .itemOptionInfoList(List.of(
            AdminRequest.ItemOption.builder()
                .itemOptionName("S")
                .itemOptionPrice(0)
                .build(),
            AdminRequest.ItemOption.builder()
                .itemOptionName("M")
                .itemOptionPrice(0)
                .build(),
            AdminRequest.ItemOption.builder()
                .itemOptionName("L")
                .itemOptionPrice(1000)
                .build()
        )).build();

    @BeforeEach
    void setUp() {
        user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        jpaUserRepository.save(user);

        product = Product.builder()
            .title(Title.create("TOY"))
            .bio(Bio.create("약국입니다~"))
            .frontImage(FrontImage.create("path", "url"))
            .backImage(BackImage.create("path", "url"))
            .bannerImage(BannerImage.create("path", "url", "path", "url"))
            .build();
        productRepository.save(product);

        item = Item.builder()
            .name(Name.create("No.1", "남바완"))
            .price(Price.create(2_990))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.LIMITED)
            .build();
        ItemImage image = ItemImage.create(item, "path", "url", 1);
        ItemImage image2 = ItemImage.create(item, "path", "url", 2);
        item.addImage(image);
        item.addImage(image2);
        item.setProduct(product);
        itemRepository.save(item);

        ITEM_등록_요청 = AdminRequest.CreateItem.builder()
            .enName("Red Pill")
            .koName("레드필")
            .price(1_000_000)
            .quantity(1)
            .itemBio("소개글입니다~")
            .status("SELLING")
            .limitType(LimitType.LIMITED)
            .images(List.of(
                    new AdminRequest.Image("path", "url", 1),
                    new AdminRequest.Image("path", "url", 1)
                )
            )
            .itemOptionGroupInfoList(List.of(itemOptionGroup_사이즈))
            .grayImage(new ImageRequest.Create("path", "url"))
            .build();
    }
}
