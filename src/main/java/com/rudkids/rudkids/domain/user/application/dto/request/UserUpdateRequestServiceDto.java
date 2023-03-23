package com.rudkids.rudkids.domain.user.application.dto.request;

public class UserUpdateRequestServiceDto {
    private int age;
    private String gender;

    private UserUpdateRequestServiceDto() {
    }

    public UserUpdateRequestServiceDto(int age, String gender) {
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
