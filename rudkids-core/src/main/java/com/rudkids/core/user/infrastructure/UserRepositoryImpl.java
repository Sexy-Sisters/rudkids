package com.rudkids.core.user.infrastructure;

import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.domain.UserRepository;
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
        ProfileImage image = ProfileImage.create("", oauthUser.picture());

        var user = User.builder()
            .email(oauthUser.email())
            .name(userName)
            .profileImage(image)
            .build();
        return userRepository.save(user);
    }

    @Override
    public List<String> getImageFileNames() {
        return userRepository.findPathsByDeletedTrue();
    }

    @Override
    public boolean existsUser(UUID userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public List<User> getUsers(String email) {
        return userRepository.findByEmailContaining(email);
    }
}
