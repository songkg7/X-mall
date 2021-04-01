package com.xmall.xmall.form;

import lombok.Data;

@Data
public class OrderForm {

    private String itemName;

    private int price;

    private int amount;

    private String orderItemSize;

    private String postcode; // 우편번호

    private String address;     // 주소

    private String detailAddress;

    private String extraAddress;



}
