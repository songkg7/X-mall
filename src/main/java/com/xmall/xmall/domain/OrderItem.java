package com.xmall.xmall.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 당시 가격
    private int amount; // 주문 당시 수량

    // 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int amount) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setAmount(amount);

        // 주문한 수량만큼 재고를 감소
        item.removeStock(amount);

        return orderItem;
    }

    // 비즈니스 로직
    public void cancel() {
        getItem().addStock(amount);
    }

    /*
    주문상품 전체가격 조회
    */
    public int getTotalPrice() {
        return getOrderPrice() * getAmount();
    }
}
