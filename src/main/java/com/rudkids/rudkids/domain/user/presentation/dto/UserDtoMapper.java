package com.rudkids.rudkids.domain.user.presentation.dto;

import com.rudkids.rudkids.domain.user.application.dto.request.UserUpdateServiceDto;
import com.rudkids.rudkids.domain.user.presentation.dto.request.SignUpRequest;
import com.rudkids.rudkids.domain.user.presentation.dto.request.UserUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserUpdateServiceDto of(SignUpRequest request) {
        return new UserUpdateServiceDto(request.getAge(), request.getGender());
    }

    public UserUpdateServiceDto of(UserUpdateDto request) {
        return new UserUpdateServiceDto(request.getAge(), request.getGender());
    }
}
