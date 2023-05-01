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

    @Embedded
    private Name name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private ProfileImage profileImage;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    protected User() {
    }

    @Builder
    private User(String email, Name name, String gender,
                 int age, PhoneNumber phoneNumber, ProfileImage profileImage, SocialType socialType) {
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

    public void update(Name name, PhoneNumber phoneNumber, ProfileImage profileImage) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public void changeRole(RoleType role) {
        this.roleType = RoleType.validate(role);
    }

    public void order(Order order) {
        orders.add(order);
    }

    public boolean isCustomProfileImage() {
        return profileImage.isCustomImage();
    }

    public String getProfileImagePath() {
        return profileImage.getPath();
    }

    public String getProfileImageUrl() {
        return profileImage.getUrl();
    }

    public String getName() {
        return name.getValue();
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }
}
