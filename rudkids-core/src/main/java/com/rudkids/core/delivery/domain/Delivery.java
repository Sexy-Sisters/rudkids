package com.rudkids.core.delivery.domain;

import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.exception.DifferentUserException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "delivery_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String receiverName;

    private String receiverPhone;

    @Embedded
    private Address address;

    private String message;

    private boolean basic;

    @Builder
    public Delivery(String receiverName, String receiverPhone, Address address, String message, boolean isBasic) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.message = message;
        this.basic = isBasic;
    }

    public void registerUser(User user) {
        this.user = user;
        user.addDeliveryAddress(this);
    }

    public void changeBasic() {
        basic = true;
        user.changeDeliveryBasic(this);
    }

    public String getZipCode() {
        return address.getZipCode();
    }

    public String getAddress() {
        return address.getAddress();
    }

    public String getExtraAddress() {
        return address.getExtraAddress();
    }

    public String getFullAddress() {
        return address.toString();
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public void checkChangeBasicDelivery() {
        if(basic) {
            user.changeDeliveryBasic(this);
        }
    }

    public void changeBasicFalse() {
        basic = false;
    }

    public void update(String receiverName, String receiverPhone, Address address, String message) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.message = message;
    }
}
