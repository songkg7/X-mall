package com.xmall.xmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import com.sun.istack.NotNull;
import java.time.LocalDateTime;

// table create
@Entity
@Getter
@Setter
public class MyReview {

    @Id
    @GeneratedValue
    @Column(name="review_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;



    private String itemName; // 아이템명

    private String orderItemSize; // 주문 사이즈

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account; // 작성자

    @NotNull
    private String starRate; // 별점 숫자


    private String StarBlack; // 별점 검정색


    private String StarGray; // 별점 회색

    @NotNull
    private String subject; // 제목

    @NotNull
    private String mainText; // 본문

    private LocalDateTime createTime; // 등록일

    @Column(columnDefinition = "bigint default 0")
    private Long viewCount; // 조회수

    public static MyReview createReview(Account account, String subject, String mainText, String itemName, String orderItemSize, String starRate,String starBlack, String starGray) {
        MyReview myReview = new MyReview();
        myReview.setAccount(account);
        myReview.setSubject(subject);
        myReview.setMainText(mainText);
        myReview.setCreateTime(LocalDateTime.now());
        myReview.setViewCount(0L);
        myReview.setItemName(itemName);
        myReview.setOrderItemSize(orderItemSize);
        myReview.setStarRate(starRate);
        myReview.setStarBlack(starBlack);
        myReview.setStarGray(starGray);
        return myReview;
    }
}

