package com.rudkids.rudkids.interfaces.user.dto;

public class UserRequest {

        public record SignUp(int age, String gender) {
    }

        public record Update(int age, String gender) {
    }
}
