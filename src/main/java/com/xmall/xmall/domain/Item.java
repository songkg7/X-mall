package com.xmall.xmall.domain;

import com.xmall.xmall.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String subTitle;

    // 상품 상세설명
    @Lob
    private String description;

    @Lob
    private String itemImage;

    // 상품 등록일
    private LocalDateTime CreateAt;


    /*
    stock 증가
    */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
    stock 감소
    */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity-quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
