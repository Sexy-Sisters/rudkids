package com.rudkids.rudkids.domain.user.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name = "tbl_user")
public class User extends AbstractEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private final UUID id = UlidCreator.getMonotonicUlid().toUuid();

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

    public RoleType getRoleType() {
        return roleType;
    }

    void getAdminPermission() {
        this.roleType = RoleType.ADMIN;
    }
}
