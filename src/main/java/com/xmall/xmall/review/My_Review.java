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
public class My_Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account; // 작성자

    @NotNull
    private String subject; // 제목

    @NotNull
    @Lob
    private String mainText; // 본문

    private LocalDateTime createTime; // 등록일

    @Column(columnDefinition = "bigint default 0")
    private Long viewCount; // 조회수

    public static My_Review createReview(Account account, String subject, String mainText) {
        My_Review my_review = new My_Review();
        my_review.setAccount(account);
        my_review.setSubject(subject);
        my_review.setMainText(mainText);
        my_review.setCreateTime(LocalDateTime.now());
        my_review.setViewCount(0L);

        return my_review;
    }
}

