package com.xmall.xmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsController {

    @GetMapping("/cs/asview")
    public String asview() {
        return "cs/asview";
    }

    @GetMapping("/cs/cancel_refund")
    public String cancel_refund() {
        return "cs/cancel_refund";
    }

    @GetMapping("/cs/delivery")
    public String delivery() {
        return "cs/delivery";
    }

    @GetMapping("/cs/member_info")
    public String member_info() {
        return "cs/member_info";
    }

    @GetMapping("/cs/member_service")
    public String member_service() {
        return "cs/member_service";
    }

    @GetMapping("/cs/notice")
    public String notice() {
        return "cs/notice";
    }

    @GetMapping("/cs/order_file")
    public String order_file() {
        return "cs/order_file";
    }

    @GetMapping("/cs/refund_service")
    public String refund_service() {
        return "cs/refund_service";
    }

    @GetMapping("/cs/main")
    public String main() {
        return "cs/cus_center_main";
    }
}
