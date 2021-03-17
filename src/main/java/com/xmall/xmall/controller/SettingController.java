package com.xmall.xmall.controller;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SettingController {

    @GetMapping("/asview")
    public String asview() {
        return "cs/asview";
    }

    @GetMapping("/cancel_refund")
    public String cancel_refund() {
        return "cs/cancel_refund";
    }

    @GetMapping("/delivery")
    public String delivery() {
        return "cs/delivery";
    }

    @GetMapping("/member_info")
    public String member_info() {
        return "cs/member_info";
    }

    @GetMapping("/member_service")
    public String member_service() {
        return "cs/member_service";
    }

    @GetMapping("/notice")
    public String notice() {
        return "cs/notice";
    }

    @GetMapping("/order_file")
    public String order_file() {
        return "cs/order_file";
    }

    @GetMapping("/refund_service")
    public String refund_service() {
        return "cs/refund_service";
    }

    @GetMapping("/main")
    public String main() {
        return "cs/cus_center_main";
    }

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
