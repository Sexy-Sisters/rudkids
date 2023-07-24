package com.rudkids.core.admin.dto;

import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.video.domain.Video;
import com.rudkids.core.video.domain.VideoBio;
import com.rudkids.core.video.domain.VideoImage;
import lombok.Builder;

import java.util.List;

public class AdminRequest {

    public record ChangeUserRole(String roleType) {}

    public record CreateProduct(
        String title,
        String productBio,
        ImageRequest.Create frontImage,
        ImageRequest.Create backImage,
        List<BannerImage> bannerImages
    ) {}

    public record ChangeProductStatus(String status) {}

    public record UpdateProduct(
        String title,
        String productBio,
        ImageRequest.Create frontImage,
        ImageRequest.Create backImage,
        List<BannerImage> bannerImages
    ) {}

    public record BannerImage(
        String path,
        String url,
        int ordering
    ) {}

    @Builder
    public record CreateItem(
        String enName,
        String koName,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<Image> images,
        List<ItemOptionGroup> itemOptionGroupList,
        ImageRequest.Create grayImage
    ) {}

    @Builder
    public record UpdateItem(
        String enName,
        String koName,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        List<Image> images,
        List<ItemOptionGroup> itemOptionGroupList,
        ImageRequest.Create grayImage
    ) {}

    @Builder
    public record ItemOptionGroup(
        String itemOptionGroupName,
        List<ItemOption> itemOptionList,
        int ordering
    ) {
    }

    @Builder
    public record ItemOption(
        String itemOptionName,
        int itemOptionPrice,
        int ordering
    ) {}

    public record Image(
        String path,
        String url,
        int ordering
    ) {}

    @Builder
    public record ChangeItemStatus(String itemStatus) {}

    public record CreateVideo(
        String imagePath,
        String imageUrl,
        String bio,
        String videoUrl,
        String itemName
    ) {
        public Video toEntity() {
            var image = VideoImage.create(imagePath, imageUrl);
            var bio = VideoBio.create(bio());
            return new Video(image, bio, videoUrl, itemName);
        }
    }

    public record UpdateVideo(
        ImageRequest.Create image,
        String bio,
        String videoUrl,
        String itemName
    ) {}

    public record DeliveryTrackingNumber(String trackingNumber) {}
}
