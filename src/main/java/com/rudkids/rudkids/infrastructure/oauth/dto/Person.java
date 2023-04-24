package com.rudkids.rudkids.infrastructure.oauth.dto;

import java.util.List;

public class Person {
    private final List<Gender> genders;
    private final List<Birthday> birthdays;
    private final List<PhoneNumber> phoneNumbers;

    public Person(List<Gender> genders, List<Birthday> birthdays, List<PhoneNumber> phoneNumbers) {
        this.genders = genders;
        this.birthdays = birthdays;
        this.phoneNumbers = phoneNumbers;
    }

    public String getGender() {
        if(genders == null) {
            return null;
        }
        return genders.get(0).getValue();
    }

    public int getAge() {
        if(birthdays == null) {
            return 0;
        }
        return birthdays.get(0).getYear();
    }

    public String getPhoneNumber() {
        if(phoneNumbers == null) {
            return null;
        }
        return phoneNumbers.get(0).getValue();
    }
}