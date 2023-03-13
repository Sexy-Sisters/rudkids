package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.util.BaseEntityId;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "tbl_user")
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

    @Getter
    @RequiredArgsConstructor
    public enum RoleType {
        USER("일반 사용자"), ADMIN("관리자");

        private final String description;
    }

    void getUserPermission() {
        this.roleType = RoleType.USER;
    }

    void getAdminPermission() {
        this.roleType = RoleType.ADMIN;
    }

}
