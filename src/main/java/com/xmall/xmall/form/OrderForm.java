package com.xmall.xmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderForm {

    private String itemName;

    private int price;

    private int amount;

    @NotBlank
    private String orderItemSize;

    @NotBlank
    private String postcode; // 우편번호

    @NotBlank
    private String address;     // 주소

    @NotBlank
    private String detailAddress;

    private String extraAddress;

    @NotBlank
    private String recipientName;

    @NotBlank
    private String recipientPhone;

}
