package com.rudkids.core.user.service;

import com.rudkids.core.community.dto.CommunityResponse;
import com.rudkids.core.image.service.ImageDeletedEvent;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.dto.UserRequest;
import com.rudkids.core.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void update(UUID userId, UserRequest.Update request) {
        var user = userRepository.getUser(userId);
        var name = UserName.create(request.name());
        var profileImage = ProfileImage.create(request.profileImagePath(), request.profileImageUrl());
        user.update(name, profileImage);
        deleteImage(user);
    }

    private void deleteImage(User user) {
        if(!user.isDefaultImage()) {
            eventPublisher.publishEvent(new ImageDeletedEvent(user.getProfileImagePath()));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse.Info get(UUID userId) {
        var user = userRepository.getUser(userId);
        return new UserResponse.Info(user);
    }

    @Override
    public List<CommunityResponse.Main> getMyCommunities(UUID userId) {
        var user = userRepository.getUser(userId);
        return user.getCommunities().stream()
            .map(CommunityResponse.Main::new)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse.Address> getAddresses(UUID userId) {
        var user = userRepository.getUser(userId);
        return user.getDeliveries().stream()
            .map(UserResponse.Address::new)
            .toList();
    }
}
