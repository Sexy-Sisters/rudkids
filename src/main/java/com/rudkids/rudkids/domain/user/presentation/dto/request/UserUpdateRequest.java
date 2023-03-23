package com.rudkids.rudkids.domain.user.presentation.dto.request;

public class UserUpdateRequest {
    private int age;
    private String gender;

    private UserUpdateRequest() {
    }

    public UserUpdateRequest(int age, String gender) {
        this.age = age;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
