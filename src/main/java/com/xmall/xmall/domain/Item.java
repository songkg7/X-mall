package com.xmall.xmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    // 상품명
    @Column(unique = true, nullable = false)
    private String name;

    // 가격
    @Column(nullable = false)
    private int price;

    // 재고
    private int stockQuantity;

    // 상품 상세설명
    @Lob
    private String description;

    @Lob
    private String itemImage;

}
