package com.rudkids.rudkids.infrastructure.oauth.dto;

public class PhoneNumber {
    private String value;

    public PhoneNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
