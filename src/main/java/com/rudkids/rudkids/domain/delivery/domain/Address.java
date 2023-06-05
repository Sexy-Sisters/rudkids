package com.rudkids.rudkids.domain.delivery.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "zip_code")
    private String zipCode;

    private Address(String address1, String address2, String zipCode) {
        this.address1 = address1;
        this.address2 = address2;
        this.zipCode = zipCode;
    }

    public static Address create(String address1, String address2, String zipCode) {
        validate(address1, address2, zipCode);
        return new Address(address1, address2, zipCode);
    }

    private static void validate(String address1, String address2, String zipCode) {
        if (address1 == null || address2 == null ||
            address1.isBlank() || address2.isBlank() ||
            zipCode == null || zipCode.isBlank()) {
            throw new InvalidNameException();
        }
    }
}
