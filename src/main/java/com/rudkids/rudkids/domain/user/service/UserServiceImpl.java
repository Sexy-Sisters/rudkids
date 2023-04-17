package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.domain.user.UserCommand;
import com.rudkids.rudkids.domain.user.UserReader;
import com.rudkids.rudkids.domain.user.domain.Age;
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
    public void update(UUID id, UserCommand.Update request) {
        var findUser = userReader.getUser(id);
        var age = Age.create(request.age());
        findUser.update(age, request.gender());
    }
}
