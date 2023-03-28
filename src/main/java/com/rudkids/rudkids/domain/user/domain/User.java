package com.rudkids.rudkids.domain.user.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.order.domain.Order;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_user")
public class User extends AbstractEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
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

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    protected User() {
    }

    @Builder
    private User(String email, String name, Age age, Gender gender, SocialType socialType) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.socialType = socialType;
    }

    public UUID getId() {
        return id;
    }

    public int getAge() {
        return age.getValue();
    }

    public Gender getGender() {
        return gender;
    }

    public void updateAdditionalInfo(Age age, String gender) {
        this.age = age;
        this.gender = Gender.toEnum(gender);
    }

    public void order(Order order) {
        orders.add(order);
    }
}
