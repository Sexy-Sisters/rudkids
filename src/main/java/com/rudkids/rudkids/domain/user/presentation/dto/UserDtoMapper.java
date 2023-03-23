package com.rudkids.rudkids.domain.user.presentation.dto;

import com.rudkids.rudkids.domain.user.application.dto.request.UserUpdateRequestServiceDto;
import com.rudkids.rudkids.domain.user.presentation.dto.request.SignUpRequest;
import com.rudkids.rudkids.domain.user.presentation.dto.request.UserUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserUpdateRequestServiceDto of(SignUpRequest request) {
        return new UserUpdateRequestServiceDto(request.getAge(), request.getGender());
    }

    public UserUpdateRequestServiceDto of(UserUpdateRequest request) {
        return new UserUpdateRequestServiceDto(request.getAge(), request.getGender());
    }
}
