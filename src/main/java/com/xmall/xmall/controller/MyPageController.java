package com.xmall.xmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    // A/S 접수 안내
    @GetMapping("/as_infoguide")
    public String as_infoguide() {
        return "mypage/as_infoguide";
    }

    // 쿠폰
    @GetMapping("/coupon")
    public String coupon() {
        return "mypage/coupon";
    }

    // 주문배송
    @GetMapping("/order_delivery")
    public String order_delivery() {
        return "mypage/order_delivery";
    }

    // 취소/반품
    @GetMapping("/return_cancle")
    public String oreturn_cancle() {
        return "mypage/return_cancle";
    }

    // 최근 주문 내역     *url 로 접속해야 함
    // TODO: 버튼이 필요할 것
    @GetMapping("/my_page")
    public String side_mypage() {
        return "mypage/side_mypage";
    }
}
