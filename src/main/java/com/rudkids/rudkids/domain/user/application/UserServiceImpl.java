package com.rudkids.rudkids.domain.user.application;

import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.dto.request.SignUpRequest;
import com.rudkids.rudkids.domain.user.exception.NotFoundUserException;
import com.rudkids.rudkids.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void signUp(UUID id, SignUpRequest request) {
        User findUser = userRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);

        Age age = Age.create(request.getAge());
        findUser.updateUserAdditionalInfo(age, request.getGender());
    }
}
