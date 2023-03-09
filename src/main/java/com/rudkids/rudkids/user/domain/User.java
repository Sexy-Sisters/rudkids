package com.rudkids.rudkids.user.domain;

import com.rudkids.rudkids.util.BaseEntityId;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class User extends BaseEntityId {

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    protected User() {
    }

    @Builder
    public User(Email email, Password password, Name name, PhoneNumber phoneNumber, RoleType roleType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.roleType = roleType;
    }

}
