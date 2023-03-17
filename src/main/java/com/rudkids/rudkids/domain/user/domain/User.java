package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.util.BaseEntityId;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "tbl_user")
@Getter
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
    public User(Email email, Password password, Name name, PhoneNumber phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    void getAdminPermission() {
        this.roleType = RoleType.ADMIN;
    }
}
