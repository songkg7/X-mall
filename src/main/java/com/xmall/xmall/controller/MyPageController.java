package com.xmall.xmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    @GetMapping("/as_infoguide")
    public String as_infoguide() {
        return "mypage/as_infoguide";
    }

    @GetMapping("/coupon")
    public String coupon() {
        return "mypage/coupon";
    }

    @GetMapping("/order_delivery")
    public String order_delivery() {
        return "mypage/order_delivery";
    }

    @GetMapping("/return_cancle")
    public String oreturn_cancle() {
        return "mypage/return_cancle";
    }

    @GetMapping("/side_mypage")
    public String side_mypage() {
        return "mypage/side_mypage";
    }
}
