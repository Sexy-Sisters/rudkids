package com.rudkids.core.user.domain;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.order.domain.Order;
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

    @Embedded
    private ProfileImage profileImage;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final List<Order> orders = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private final List<Delivery> deliveries = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private final List<Community> communities = new ArrayList<>();

    @Builder
    public User(String email, UserName name, PhoneNumber phoneNumber, ProfileImage profileImage) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public void validateAdminRole() {
        if (!roleType.equals(RoleType.ADMIN)) {
            throw new NotAdminRoleException();
        }
    }

    public void changeDeliveryBasic(Delivery target) {
        deliveries.stream()
            .filter(delivery -> !delivery.equals(target))
            .filter(Delivery::isBasic)
            .findFirst()
            .ifPresent(Delivery::changeBasicFalse);
    }

    public boolean hasPhoneNumber() {
        return !phoneNumber.isEmpty();
    }

    public boolean isDefaultImage() {
        return profileImage.isEmptyPath();
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

    public void changeRole(RoleType role) {
        this.roleType = role;
    }

    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void update(UserName name, ProfileImage profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }

    public void order(Order order) {
        orders.add(order);
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
