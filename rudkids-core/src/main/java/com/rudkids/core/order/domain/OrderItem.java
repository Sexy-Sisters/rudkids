package com.rudkids.core.order.domain;

import com.rudkids.core.item.domain.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@Table(name = "tbl_order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "cart_order_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String name;

    private String imageUrl;

    private int amount;

    private int price;

    @Builder
    public OrderItem(Order order, Item item, String name, String imageUrl, int amount, int price) {
        this.order = order;
        this.item = item;
        this.name = name;
        this.imageUrl = imageUrl;
        this.amount = amount;
        this.price = price;
    }

    public void cancel() {
        item.addQuantity(amount);
    }

    public void removeQuantity() {
        item.removeQuantity(amount);
    }
}
