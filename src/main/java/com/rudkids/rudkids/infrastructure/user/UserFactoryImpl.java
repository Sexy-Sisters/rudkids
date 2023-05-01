package com.rudkids.rudkids.infrastructure.user;

import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.domain.user.UserCommand;
import com.rudkids.rudkids.domain.user.UserFactory;
import com.rudkids.rudkids.domain.user.domain.Name;
import com.rudkids.rudkids.domain.user.domain.PhoneNumber;
import com.rudkids.rudkids.domain.user.domain.ProfileImage;
import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactoryImpl implements UserFactory {

    @Override
    public User create(AuthCommand.OAuthUser oAuthUser) {
        Name name = Name.create(oAuthUser.name());
        PhoneNumber phoneNumber = PhoneNumber.create(oAuthUser.phoneNumber());
        ProfileImage profileImage = ProfileImage.create("", oAuthUser.profileImage());

        return User.builder()
            .email(oAuthUser.email())
            .name(name)
            .gender(oAuthUser.gender())
            .age(oAuthUser.age())
            .phoneNumber(phoneNumber)
            .profileImage(profileImage)
            .socialType(oAuthUser.socialType())
            .build();
    }

    @Override
    public void update(User user, UserCommand.Update command) {
        Name name = Name.create(command.name());
        PhoneNumber phoneNumber = PhoneNumber.create(command.phoneNumber());
        ProfileImage profileImage = ProfileImage.create(command.profileImagePath(), command.profileImageUrl());
        user.update(name, phoneNumber, profileImage);
    }
}
