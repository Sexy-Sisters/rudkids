package com.rudkids.rudkids.interfaces.user.dto;

import lombok.Getter;

public class UserRequest {

    @Getter
        public record SignUp(int age, String gender) {
    }

    @Getter
        public record Update(int age, String gender) {
    }
}
