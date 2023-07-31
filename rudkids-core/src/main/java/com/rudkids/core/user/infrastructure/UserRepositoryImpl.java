package com.rudkids.core.user.infrastructure;

import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository userRepository;

    @Override
    public User getUser(UUID userId) {
        return userRepository.findById(userId)
            .orElseThrow(NotFoundUserException::new);
    }

    public User getUser(AuthUser.OAuth oauthUser) {
        return userRepository.findByEmail(oauthUser.email())
            .orElseGet(() -> saveUser(oauthUser));
    }

    private User saveUser(AuthUser.OAuth oauthUser) {
        UserName userName = UserName.create(oauthUser.name());
        ProfileImage image = ProfileImage.createDefault(oauthUser.picture());
        PhoneNumber phoneNumber = PhoneNumber.createDefault();

        var user = User.builder()
            .email(oauthUser.email())
            .name(userName)
            .phoneNumber(phoneNumber)
            .profileImage(image)
            .build();
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmailContaining(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumberValue(phoneNumber);
    }
}
