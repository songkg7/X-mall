package com.xmall.xmall.domain;

import com.xmall.xmall.form.OrderForm;
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
    private String orderItemSize; // 주문 당시 사이즈

    private String postCode;
    private String address;

    // 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, OrderForm orderForm) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setAmount(orderForm.getAmount());
        orderItem.setOrderItemSize(orderForm.getOrderItemSize());
        orderItem.setPostCode(orderForm.getPostCode());
        orderItem.setAddress(orderForm.getAddress());

        // 주문한 수량만큼 재고를 감소
        item.removeStock(orderForm.getAmount());

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
