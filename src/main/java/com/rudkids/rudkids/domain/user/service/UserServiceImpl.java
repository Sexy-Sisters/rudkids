package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserReader userReader;
    private final UserMapper userMapper;

    @Override
    public void update(UUID userId, UserCommand.Update command) {
        var user = userReader.getUser(userId);
        user.update(command.toName(), command.toPhoneNumber(), command.toProfileImage());
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfo.Main find(UUID userId) {
        var user = userReader.getUser(userId);
        return userMapper.toInfo(user);
    }

    @Override
    public List<UserInfo.Addresses> findAddresses(UUID userId) {
        var user = userReader.getUser(userId);
        return user.getDeliveries().stream()
            .map(userMapper::toInfo)
            .toList();
    }
}
