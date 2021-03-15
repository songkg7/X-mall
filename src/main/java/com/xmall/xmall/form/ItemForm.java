package com.xmall.xmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemForm {

    @NotBlank
    private String name;

    @NotNull
    private int price;

    private int stockQuantity;

    private String description;

    private String itemImage;

}
