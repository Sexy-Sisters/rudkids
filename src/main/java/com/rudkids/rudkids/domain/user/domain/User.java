package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import com.rudkids.rudkids.domain.user.exception.NotAdminOrPartnerRoleException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_user")
@EqualsAndHashCode(callSuper = false)
@Getter
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_image")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    protected User() {
    }

    @Builder
    private User(String email, String name, String gender,
                 int age, String phoneNumber, String profileImage, SocialType socialType) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.socialType = socialType;
    }

    public void changeAuthorityAdmin() {
        roleType = RoleType.ADMIN;
    }

    public void changeAuthorityPartner() {
        roleType = RoleType.PARTNER;
    }

    public void changeAuthorityUser() {
        roleType = RoleType.USER;
    }

    public void validateAdminRole() {
        if (!roleType.equals(RoleType.ADMIN)) {
            throw new NotAdminRoleException();
        }
    }

    public void validateAdminOrPartnerRole() {
        if (!roleType.equals(RoleType.PARTNER) && !roleType.equals(RoleType.ADMIN)) {
            throw new NotAdminOrPartnerRoleException();
        }
    }

    public void update(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changeRole(RoleType role) {
        this.roleType = RoleType.validate(role);
    }

    public void order(Order order) {
        orders.add(order);
    }
}
