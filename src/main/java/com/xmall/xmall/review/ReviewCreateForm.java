package com.xmall.xmall.review;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ReviewCreateForm {

    @NotEmpty(message = "제목을 입력해주세요")
    @Column(columnDefinition="TEXT")
    private String subject;

    @NotEmpty(message = "본문 내용을 입력해주세요")
    @Column(columnDefinition="TEXT")
    private String mainText;

}
