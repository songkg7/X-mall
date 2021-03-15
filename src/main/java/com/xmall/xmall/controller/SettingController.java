package com.xmall.xmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingController {

    // FIXME: order-payment url 적절하지 않음
    @GetMapping("/mypage/order-payment")
    public String orderPayment() {return "mypage/order-payment";}

//    @GetMapping("/cs")
//    public String cs() {
//        return ""
//    }

}
