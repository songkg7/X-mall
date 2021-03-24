package com.xmall.xmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    private String address;

    @Enumerated(EnumType.STRING) // EnumType option 은 반드시 String 으로 넣을 것. 그래야 중간에 다른 옵션이 생겨도 순서에 영향 받지 않는다.
    private DeliveryStatus status; // READY, COMP

}
