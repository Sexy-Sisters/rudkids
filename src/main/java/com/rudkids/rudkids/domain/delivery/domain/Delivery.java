package com.rudkids.rudkids.domain.delivery.domain;

import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.DifferentUserException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_delivery")
@Getter
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

    private boolean isBasic = false;

    @Builder
    public Delivery(User user, String receiverName, String receiverPhone, Address address, String message) {
        this.user = user;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.message = message;
    }

    public void changeBasic() {
        isBasic = true;
    }

    public String getZipCode() {
        return address.getZipCode();
    }

    public String getAddress1() {
        return address.getAddress1();
    }

    public String getAddress2() {
        return address.getAddress2();
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public void update(String receiverName, String receiverPhone, Address address, String message) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.message = message;
    }
}
