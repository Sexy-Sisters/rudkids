package com.rudkids.rudkids.domain.user.application.dto.request;

public class UserUpdateServiceDto {
    private int age;
    private String gender;

    private UserUpdateServiceDto() {
    }

    public UserUpdateServiceDto(int age, String gender) {
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
