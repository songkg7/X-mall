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

    // TODO: Size 선택 옵션에 대해서 생각해보기
    // 사이즈
//    private List<Size> sizes = new ArrayList<>();

    // 상품 상세설명
    @Lob
    private String description;

    @Lob
    private String itemImage;

}
