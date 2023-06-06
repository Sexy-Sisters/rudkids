package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.domain.image.service.ImageService;
import com.rudkids.rudkids.domain.user.*;
import com.rudkids.rudkids.domain.user.domain.PhoneNumber;
import com.rudkids.rudkids.domain.user.domain.ProfileImage;
import com.rudkids.rudkids.domain.user.domain.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserReader userReader;
    private final ImageService imageService;
    private final UserMapper userMapper;

    @Override
    public void update(UUID userId, UserCommand.Update command) {
        var user = userReader.getUser(userId);
        imageService.delete(user);

        var name = UserName.create(command.name());
        var phoneNumber = PhoneNumber.create(command.phoneNumber());
        var profileImage = ProfileImage.create(command.profileImagePath(), command.profileImageUrl());
        user.update(name, phoneNumber, profileImage);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfo.Main find(UUID userId) {
        var user = userReader.getUser(userId);
        return userMapper.toInfo(user);
    }
}
