package com.xmall.xmall.review;



import com.xmall.xmall.domain.Account;
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

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account; // 작성자

    @NotNull
    private String subject; // 제목

    @NotNull
    @Lob
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

        return myReview;
    }
}

