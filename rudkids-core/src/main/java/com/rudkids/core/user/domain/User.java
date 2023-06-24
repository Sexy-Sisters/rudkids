package com.rudkids.core.user.domain;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.user.exception.NotAdminOrPartnerRoleException;
import com.rudkids.core.user.exception.NotAdminRoleException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_user")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

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
    public User(String email, UserName name, String gender, int age,
                PhoneNumber phoneNumber, ProfileImage profileImage, SocialType socialType) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.socialType = socialType;
    }

    public void validateAdminRole() {
        if (!roleType.equals(RoleType.ADMIN)) {
            throw new NotAdminRoleException();
        }
    }

    public void changeAuthorityAdmin() {
        this.roleType = RoleType.ADMIN;
    }

    public void changeAuthorityUser() {
        this.roleType = RoleType.USER;
    }

    public void registerOrder(Order order) {
        orders.add(order);
    }

    public boolean isAdminRole() {
        return roleType == RoleType.ADMIN;
    }

    public void validateAdminOrPartnerRole() {
        if (!roleType.equals(RoleType.PARTNER) && !roleType.equals(RoleType.ADMIN)) {
            throw new NotAdminOrPartnerRoleException();
        }
    }

    public void changeRole(String role) {
        this.roleType = RoleType.toEnum(role);
    }

    public void update(UserName name, PhoneNumber phoneNumber, ProfileImage profileImage) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public void order(Order order) {
        orders.add(order);
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

    public void deleteUserImage() {
        profileImage.deleteImage();
    }
}
