package com.rudkids.rudkids.infrastructure.oauth.dto.google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class GoogleInformationResponse {
    private List<Names> names;
    private List<Photos> photos;
    private List<Gender> genders;
    private List<Birthday> birthdays;
    private List<EmailAddresses> emailAddresses;
    private List<PhoneNumber> phoneNumbers;

    public String getName() {
        return names.get(0).getDisplayName();
    }

    public String getPhoto() {
        return photos.get(0).getUrl();
    }

    public String getEmail() {
        return emailAddresses.get(0).getValue();
    }

    public String getGender() {
        return genders.get(0).getValue();
    }

    public int getAge() {
        if(birthdays == null) {
            return 0;
        }
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        int nowYear = Integer.parseInt(now.format(formatter));
        return (nowYear - birthdays.get(0).getDate().year) + 1;
    }

    public String getPhoneNumber() {
        if(phoneNumbers == null) {
            return null;
        }
        return phoneNumbers.get(0).getValue();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class Names {
        private String displayName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class Photos {
        private String url;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class EmailAddresses {
        private String value;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class Gender {
        private String value;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class PhoneNumber {
        private String value;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class Birthday {
        private Date date;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class Date {
        private int year;
    }
}