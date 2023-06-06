package com.rudkids.rudkids.domain.community;

import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.community.domain.CommunityImage;
import com.rudkids.rudkids.domain.community.domain.Content;
import com.rudkids.rudkids.domain.community.domain.Title;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;
import lombok.Builder;

public class CommunityCommand {

    @Builder
    public record Create(
        String title,
        String content,
        String type,
        ImageRequest image
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
        ImageRequest image
    ) {
        public Title toTitle() {
            return Title.create(title);
        }

        public Content toContent() {
            return Content.create(content);
        }

        public CommunityImage toImage() {
            return CommunityImage.create(image.path(), image.url());
        }
    }
}
