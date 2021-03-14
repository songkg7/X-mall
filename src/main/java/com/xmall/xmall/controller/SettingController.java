package com.xmall.xmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {


    @GetMapping("/mypage/order-payment")
    public String orderPayment() {return "mypage/order-payment";}


}
 