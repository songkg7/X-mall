package com.xmall.xmall.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CommentForm {
    @NotEmpty(message = "본문 내용을 입력해주세요.")
    @Column(columnDefinition="TEXT")
    private String commentText;

}
