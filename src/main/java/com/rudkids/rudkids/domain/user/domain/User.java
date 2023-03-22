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

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Age age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    protected User() {
    }

    private User(String email, String name, Age age, Gender gender, SocialType socialType) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.socialType = socialType;
    }

    public static User create(String email, String name, Age age, Gender gender, SocialType socialType) {
        return new User(email, name, age, gender, socialType);
    }

    public UUID getId() {
        return id;
    }

    public void updateAdditionalInfo(Age age, String gender) {
        this.age = age;
        this.gender = Gender.toEnum(gender);
    }
}
