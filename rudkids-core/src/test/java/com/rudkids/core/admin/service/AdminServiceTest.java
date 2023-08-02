package com.rudkids.core.admin.service;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.common.fixtures.admin.AdminServiceFixtures;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.item.exception.ItemNotFoundException;
import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductStatus;
import com.rudkids.core.product.exception.ProductNotFoundException;
import com.rudkids.core.user.domain.RoleType;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AdminServiceTest extends AdminServiceFixtures {

    @Nested
    @DisplayName("유저를 검색한다")
    class searchUsers {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            var response = adminService.searchUsers("swnam0729@gmail.com");

            //then
            assertThat(response).hasSize(0);
        }
    }

    @Nested
    @DisplayName("유저의 권한을 변경한다")
    class changeUserRole {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            AdminRequest.ChangeUserRole request = new AdminRequest.ChangeUserRole("ADMIN");

            //when
            adminService.changeUserRole(user.getId(), request);

            //then
            assertThat(user.getRoleType()).isEqualTo(RoleType.ADMIN);
        }
    }

    @Nested
    @DisplayName("프로덕트를 생성한다")
    class createProduct {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            AdminRequest.CreateProduct command = new AdminRequest.CreateProduct(
                "Strange Drugstore222",
                "약쟁이가 약팝니다~~~~",
                new ImageRequest.Create("image", "image.jpg"),
                new ImageRequest.Create("image", "image.jpg"),
                List.of(
                    new AdminRequest.BannerImage("image", "image.jpg", 1)
                ),
                new ImageRequest.Create("new path", "new url"),
                false
            );

            //when
            UUID productId = adminService.createProduct(command);

            //then
            Product findProduct = productRepository.get(productId);
            assertAll(
                () -> assertThat(findProduct.getTitle()).isEqualTo("Strange Drugstore222"),
                () -> assertThat(findProduct.getProductBio()).isEqualTo("약쟁이가 약팝니다~~~~")
            );
        }
    }

    @Nested
    @DisplayName("프로덕트의 상태를 변경한다")
    class changeProductStatus {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            AdminRequest.ChangeProductStatus request = new AdminRequest.ChangeProductStatus("OPEN");
            adminService.changeProductStatus(product.getId(), request);

            //then
            assertThat(product.getProductStatus()).isEqualTo(ProductStatus.OPEN);
        }

        @Test
        @DisplayName("실패: 존재하지 않는 프로덕트")
        void fail() {
            //given
            var productId = UUID.randomUUID();

            //when, then
            assertThatThrownBy(() -> adminService.changeProductStatus(productId, new AdminRequest.ChangeProductStatus("OPEN")))
                .isInstanceOf(ProductNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("프로덕트를 수정한다")
    class updateProduct {

        @Test
        @Disabled("update method 안에 s3 삭제로직이 있음")
        @DisplayName("성공")
        void success() {
            //given
            AdminRequest.UpdateProduct request = new AdminRequest.UpdateProduct(
                "new title",
                "새로운 productBio",
                new ImageRequest.Create("new path", "new url"),
                new ImageRequest.Create("new path", "new url"),
                List.of(
                    new AdminRequest.BannerImage("new path", "new url", 1),
                    new AdminRequest.BannerImage("new path", "new url", 2)
                ),
                new ImageRequest.Create("new path", "new url")
            );

            //when
            UUID productId = product.getId();
            adminService.updateProduct(productId, request);

            //given
            int page = 0;
            int size = 4;
            Pageable pageable = PageRequest.of(page, size);
            var foundProduct = productService.get(user.getId(), productId, pageable);
            assertAll(() -> {
                assertThat(foundProduct.title()).isEqualTo("new title");
                assertThat(foundProduct.bio()).isEqualTo("새로운 productBio");
                assertThat(foundProduct.frontImage().path()).isEqualTo("new path");
                assertThat(foundProduct.frontImage().url()).isEqualTo("new url");
                assertThat(foundProduct.backImage().path()).isEqualTo("new path");
                assertThat(foundProduct.backImage().url()).isEqualTo("new url");
            });
        }
    }

    @Nested
    @DisplayName("프로덕트를 삭제한다")
    class deleteProduct {

        @Test
        @Disabled("update method 안에 s3 삭제로직이 있음")
        @DisplayName("성공")
        void success() {
            //given

            //when
            adminService.deleteProduct(product.getId());

            //when
            assertThatThrownBy(() -> productRepository.get(product.getId()))
                .isInstanceOf(ProductNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("아이템을 생성한다")
    class createItem {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            String itemName = adminService.createItem(product.getId(), ITEM_등록_요청);

            //then
            Item findItem = itemRepository.getByEnNme(itemName);
            assertAll(
                () -> assertThat(findItem.getEnName()).isEqualTo("Red Pill"),
                () -> assertThat(findItem.getPrice()).isEqualTo(1_000_000),
                () -> assertThat(findItem.getQuantity()).isEqualTo(1),
                () -> assertThat(findItem.getItemBio()).isEqualTo("소개글입니다~"),
                () -> assertThat(findItem.getLimitType()).isEqualTo(LimitType.LIMITED),
                () -> assertThat(findItem.getItemStatus()).isEqualTo(ItemStatus.SELLING),
                () -> assertThat(findItem.getItemOptionGroups()).hasSize(1)
            );
        }

        @Test
        @DisplayName("실패: 존재하지 않는 프로덕트")
        void fail() {
            //given
            var invalidProductId = UUID.randomUUID();

            //when, then
            AssertionsForClassTypes.assertThatThrownBy(() -> adminService.createItem(invalidProductId, ITEM_등록_요청))
                .isInstanceOf(ProductNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("아이템을 수정한다")
    class updateItem {

        @Test
        @Disabled("update method 안에 s3 삭제로직이 있음")
        @DisplayName("성공")
        void success() {
            //given
            var itemName = item.getEnName();

            //when
            adminService.updateItem(itemName, 아이템_수정_요청());

            //then
            assertAll(
                () -> assertThat(item.getEnName()).isEqualTo("새로운 item"),
                () -> assertThat(item.getItemBio()).isEqualTo("소개글"),
                () -> assertThat(item.getPrice()).isEqualTo(1000),
                () -> assertThat(item.getQuantity()).isEqualTo(100),
                () -> assertThat(item.getLimitType()).isEqualTo(LimitType.LIMITED)
            );
        }

        @Test
        @DisplayName("실패: 존재하지 않는 프로덕트")
        void fail() {
            //given
            var invalidItemName = "invalid";

            //when, then
            AssertionsForClassTypes.assertThatThrownBy(() -> adminService.updateItem(invalidItemName, 아이템_수정_요청()))
                .isInstanceOf(ItemNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("아이템 상태를 변경한다")
    class changeItemStatus {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var itemName = item.getEnName();

            var itemStatus = "SELLING";

            //when
            AdminRequest.ChangeItemStatus statusRequest = new AdminRequest.ChangeItemStatus(itemStatus);
            adminService.changeItemStatus(itemName, statusRequest);

            //then
            assertThat(item.getItemStatus()).isEqualTo(ItemStatus.SELLING);
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() {
            //given
            var invalidItemName = "invalid";
            var status = "SELLING";

            //when, then
            AdminRequest.ChangeItemStatus statusRequest = new AdminRequest.ChangeItemStatus(status);
            AssertionsForClassTypes.assertThatThrownBy(() -> adminService.changeItemStatus(invalidItemName, statusRequest))
                .isInstanceOf(ItemNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("아이템을 삭제한다")
    class deleteItem {

        @Test
        @Disabled("update method 안에 s3 삭제로직이 있음")
        @DisplayName("성공")
        void success() {
            //given

            //when
            adminService.deleteItem(item.getEnName());

            //then
            assertThatThrownBy(() -> itemRepository.getByEnNme(item.getEnName()))
                .isInstanceOf(ItemNotFoundException.class);
        }

        @Test
        @DisplayName("실패: 존재하지 않는 아이템")
        void fail() {
            //given
            String invalidItemName = "invalid";

            //when, then
            AssertionsForClassTypes.assertThatThrownBy(() -> adminService.deleteItem(invalidItemName))
                .isInstanceOf(ItemNotFoundException.class);
        }
    }
}
