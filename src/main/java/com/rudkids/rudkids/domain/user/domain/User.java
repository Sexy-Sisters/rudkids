package com.rudkids.rudkids.domain.user.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.common.AbstractEntity;
import jakarta.persistence.*;

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
    private Name name;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    protected User() {
    }

    private User(Email email, Name name, SocialType socialType) {
        this.email = email;
        this.name = name;
        this.socialType = socialType;
    }

    public static User create(Email email, Name name, SocialType socialType) {
        return new User(email, name, socialType);
    }

    public UUID getId() {
        return id;
    }
}
