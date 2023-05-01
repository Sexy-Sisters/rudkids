package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.domain.image.service.ImageService;
import com.rudkids.rudkids.domain.user.UserCommand;
import com.rudkids.rudkids.domain.user.UserFactory;
import com.rudkids.rudkids.domain.user.UserReader;
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
    private final UserFactory userFactory;

    @Override
    public void update(UUID userId, UserCommand.Update command) {
        var user = userReader.getUser(userId);
        imageService.delete(user);
        userFactory.update(user, command);
    }
}
