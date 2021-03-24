package com.xmall.xmall.domain;


import com.xmall.xmall.form.OrderForm;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String orderItemSize; // 주문 당시 사이즈

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account; // 작성자

    @NotNull
    private String subject; // 제목

    @NotNull
    private String mainText; // 본문

    private LocalDateTime createTime; // 등록일

    @Column(columnDefinition = "bigint default 0")
    private Long viewCount; // 조회수

    public static MyReview createReview(Account account, String subject, String mainText) {
        MyReview myReview = new MyReview();
        myReview.setAccount(account);
        myReview.setSubject(subject);
        myReview.setMainText(mainText);
        myReview.setCreateTime(LocalDateTime.now());
        myReview.setViewCount(0L);
//        myReview.setOrderItemSize();


        return myReview;
    }
}

