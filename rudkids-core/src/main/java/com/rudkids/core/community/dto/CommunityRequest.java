package com.rudkids.core.community.dto;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.community.domain.CommunityImage;
import com.rudkids.core.community.domain.Content;
import com.rudkids.core.community.domain.Title;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.user.domain.User;
import lombok.Builder;

public class CommunityRequest {

    @Builder
    public record Create(
        String title,
        String content,
        String type,
        ImageRequest.Create image
    ) {
        public Community toEntity(User user) {
            var initTitle = Title.create(title);
            var initContent = Content.create(content);
            var initImage = CommunityImage.create(image.path(), image.url());
            return Community.create(user, initTitle, initContent, initImage);
        }
    }

    @Builder
    public record Update(
        String title,
        String content,
        ImageRequest.Create image
    ) {}
}
