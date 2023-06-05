package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.user.exception.NotAdminOrPartnerRoleException;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_user")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private UserName name;

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
    private final List<Order> orders = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private final List<Delivery> deliveries = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private final List<Community> communities = new ArrayList<>();

    @Builder
    private User(String email, UserName name, String gender,
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

    public void changeAuthorityUser() {
        roleType = RoleType.USER;
    }

    public void validateAdminRole() {
        if (!roleType.equals(RoleType.ADMIN)) {
            throw new NotAdminRoleException();
        }
    }

    public boolean isAdminRole() {
        return roleType == RoleType.ADMIN;
    }

    public void validateAdminOrPartnerRole() {
        if (!roleType.equals(RoleType.PARTNER) && !roleType.equals(RoleType.ADMIN)) {
            throw new NotAdminOrPartnerRoleException();
        }
    }

    public void signUp(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void update(UserName name, PhoneNumber phoneNumber, ProfileImage profileImage) {
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

    public void addDeliveryAddress(Delivery delivery) {
        deliveries.add(delivery);
    }

    public void writeCommunity(Community community) {
        communities.add(community);
    }
}
