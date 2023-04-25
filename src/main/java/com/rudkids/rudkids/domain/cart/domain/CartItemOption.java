package com.rudkids.rudkids.domain.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_cart_item_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CartItemOption {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "cart_item_option_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    private CartItemOption(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static CartItemOption create(String name, int price) {
        return new CartItemOption(name, price);
    }
}
