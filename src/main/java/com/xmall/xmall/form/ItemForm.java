package com.xmall.xmall.form;

import lombok.Data;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemForm {

    @NotBlank
    private String name;

    private String subTitle;

    @NotNull
    private int price;

    private int stockQuantity;


    private String description;

    @Lob
    private String itemImage;

    private String genderType;
}
