package com.rudkids.rudkids.domain.user.dto.request;

public class SignUpRequest {
    private int age;
    private String gender;

    private SignUpRequest() {
    }

    public SignUpRequest(int age, String gender) {
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
