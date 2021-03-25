package com.xmall.xmall.board;

import com.xmall.xmall.domain.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @Column(name="comment_id")
    private Long id;

    @JoinColumn(name="board_id")

    private String commentText;

    public static Comment createComment(String commentText) {
        Comment comment = new Comment();
        comment.setCommentText(commentText);
        return comment;
    }


}
