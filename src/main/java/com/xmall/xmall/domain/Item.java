package com.xmall.xmall.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue
//    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private int stockQuantity;

    // 상품 등록날짜
    private LocalDateTime resistAt;

    // image 는 용량 문제로 일단 1장만 업로드할 수 있게 한 뒤 수정
    @Lob
    private String image;

    // TODO: size 선택

}
