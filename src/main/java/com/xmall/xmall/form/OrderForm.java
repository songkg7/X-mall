package com.xmall.xmall.form;

import lombok.Data;

@Data
public class OrderForm {

    private String itemName;

    private int price;

    private int amount;

    private String orderItemSize;

    private String mainAddress;

    private String detailAddress;

}
