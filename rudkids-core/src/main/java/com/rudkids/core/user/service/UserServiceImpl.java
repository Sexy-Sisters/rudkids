package com.rudkids.core.user.service;

import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.domain.UserRepository;
import com.rudkids.core.user.dto.UserRequest;
import com.rudkids.core.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userReader;

    @Override
    public void update(UUID userId, UserRequest.Update request) {
        var user = userReader.getUser(userId);

        var name = UserName.create(request.name());
        var phoneNumber = PhoneNumber.create(request.phoneNumber());
        var profileImage = ProfileImage.create(request.profileImagePath(), request.profileImageUrl());
        user.update(name, phoneNumber, profileImage);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse.Info get(UUID userId) {
        var user = userReader.getUser(userId);
        return new UserResponse.Info(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse.Address> getAddresses(UUID userId) {
        var user = userReader.getUser(userId);
        return user.getDeliveries().stream()
            .map(UserResponse.Address::new)
            .toList();
    }
}
