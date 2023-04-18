package com.rudkids.rudkids.domain.order.domain.orderItem;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_order_item_option")
public class OrderItemOption {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "order_item_option_id")
    private UUID id;

    @JoinColumn(name = "order_item_option_group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderItemOptionGroup orderItemOptionGroup;
    private Integer ordering;
    private String itemOptionName;
    private Long ItemOptionPrice;

    @Builder
    public OrderItemOption(OrderItemOptionGroup orderItemOptionGroup, Integer ordering, String itemOptionName, Long itemOptionPrice) {
        this.orderItemOptionGroup = orderItemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        ItemOptionPrice = itemOptionPrice;
    }
}
