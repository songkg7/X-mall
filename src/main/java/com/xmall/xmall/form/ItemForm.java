package com.xmall.xmall.form;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemForm {

    @NotBlank
    private String name;

    private String subTitle;

    @NotBlank
    private int price;

    @NotBlank
    private int stockQuantity;

    private String description;

    @Column(columnDefinition="TEXT")
    private String itemImage;

    @NotNull
    private String genderType;

    private String categoryType;

    private String categoryDetail;

}
