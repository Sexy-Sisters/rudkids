package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import com.rudkids.rudkids.domain.user.exception.NotAdminOrPartnerRoleException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_user")
@EqualsAndHashCode(callSuper = false)
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

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    protected User() {
    }

    @Builder
    private User(String email, String name, String gender, int age, String phoneNumber, SocialType socialType) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.socialType = socialType;
    }

    public UUID getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public void changeAuthorityAdmin() {
        roleType = RoleType.ADMIN;
    }

    public void changeAuthorityPartner() {
        roleType = RoleType.PARTNER;
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

    public void order(Order order) {
        orders.add(order);
    }
}
