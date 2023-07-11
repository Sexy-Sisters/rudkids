package com.rudkids.core.delivery.domain;

import com.rudkids.core.user.exception.InvalidNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Column(name = "address")
    private String address;

    @Column(name = "extraAddress")
    private String extraAddress;

    @Column(name = "zip_code")
    private String zipCode;

    private Address(String address, String extraAddress, String zipCode) {
        this.address = address;
        this.extraAddress = extraAddress;
        this.zipCode = zipCode;
    }

    public static Address create(String address, String extraAddress, String zipCode) {
        validate(address, extraAddress, zipCode);
        return new Address(address, extraAddress, zipCode);
    }

    private static void validate(String address, String extraAddress, String zipCode) {
        if (address == null || extraAddress == null ||
            address.isBlank() || extraAddress.isBlank() ||
            zipCode == null || zipCode.isBlank()) {
            throw new InvalidNameException();
        }
    }
}
