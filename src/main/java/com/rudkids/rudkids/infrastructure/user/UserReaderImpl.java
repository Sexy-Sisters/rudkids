package com.rudkids.rudkids.infrastructure.user;

import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.domain.user.UserReader;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;

    @Override
    public User getUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);
    }

    @Override
    public User getUser(AuthCommand.OAuthUser oAuthUser) {
        return userRepository.findByEmail(oAuthUser.email())
            .orElseGet(() -> saveUser(oAuthUser));
    }

    private User saveUser(AuthCommand.OAuthUser oAuthUser) {
        var user = User.builder()
            .email(oAuthUser.email())
            .name(oAuthUser.name())
            .gender(oAuthUser.gender())
            .age(oAuthUser.age())
            .phoneNumber(oAuthUser.phoneNumber())
            .socialType(SocialType.GOOGLE)
            .build();
        return userRepository.save(user);
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
