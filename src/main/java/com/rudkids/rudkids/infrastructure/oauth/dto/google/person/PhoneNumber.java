package com.rudkids.rudkids.infrastructure.oauth.dto.google.person;

public class PhoneNumber {
    private String value;

    public PhoneNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
