package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.domain.user.domain.*;
import com.rudkids.rudkids.domain.user.dto.request.SignUpRequestDto;
import com.rudkids.rudkids.domain.user.exception.DuplicateEmailException;
import com.rudkids.rudkids.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UUID signUp(SignUpRequestDto signUpRequest) {
        validate(signUpRequest);

        User user = User.builder()
                .email(Email.create(signUpRequest.getEmail()))
                .password(Password.create(signUpRequest.getPassword()))
                .name(Name.create(signUpRequest.getName()))
                .phoneNumber(PhoneNumber.create(signUpRequest.getPhoneNumber()))
                .build();
        return userRepository.save(user).getId();
    }

    private void validate(SignUpRequestDto signUpRequest) {
        checkDuplicateEmail(signUpRequest);
    }

    private void checkDuplicateEmail(SignUpRequestDto signUpRequest) {
        if(userRepository.existsByEmailValue(signUpRequest.getEmail())) {
            throw new DuplicateEmailException();
        }
    }
}
