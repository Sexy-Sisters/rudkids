package com.rudkids.rudkids.domain.user.application;

import com.rudkids.rudkids.domain.user.application.dto.request.UserUpdateServiceDto;
import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserReader userReader;

    @Override
    public void update(UUID id, UserUpdateServiceDto request) {
        User findUser = userReader.getUser(id);

        Age age = Age.create(request.getAge());
        findUser.updateAdditionalInfo(age, request.getGender());
    }
}
