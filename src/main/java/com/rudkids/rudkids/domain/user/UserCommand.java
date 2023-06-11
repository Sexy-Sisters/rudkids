package com.rudkids.rudkids.domain.user;

import com.rudkids.rudkids.domain.user.domain.PhoneNumber;
import com.rudkids.rudkids.domain.user.domain.ProfileImage;
import com.rudkids.rudkids.domain.user.domain.UserName;
import lombok.Builder;

public class UserCommand {

    @Builder
    public record Update(
        String name,
        String phoneNumber,
        String profileImagePath,
        String profileImageUrl
    ) {
        public UserName toName() {
            return UserName.create(name);
        }

        public PhoneNumber toPhoneNumber() {
            return PhoneNumber.create(phoneNumber);
        }

        public ProfileImage toProfileImage() {
            return ProfileImage.create(profileImagePath, profileImageUrl);
        }
    }
}
