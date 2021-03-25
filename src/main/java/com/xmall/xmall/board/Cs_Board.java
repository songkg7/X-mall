package com.xmall.xmall.board;


import com.xmall.xmall.domain.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Cs_Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account; // 작성자

    @NotNull
    private String subject; // 제목

    @NotNull
    @Lob
    private String mainText; // 본문

    private String commentText;

    private LocalDateTime createTime;

    @Column(columnDefinition = "bigint default 0")
    private Long viewCount; // 조회수

    public static Cs_Board createBoard(Account account, String subject, String mainText) {
        Cs_Board cs_board = new Cs_Board();
        cs_board.setAccount(account);
        cs_board.setSubject(subject);
        cs_board.setMainText(mainText);
        cs_board.setCreateTime(LocalDateTime.now());
        cs_board.setViewCount(0L);

        return cs_board;
    }
    public static Cs_Board createComment(String commentText){
        Cs_Board comment = new Cs_Board();
        comment.setCommentText(commentText);
        return comment;
    }

}
