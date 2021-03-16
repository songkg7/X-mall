package com.xmall.xmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingController {

    // FIXME: order-payment url 적절하지 않음
    @GetMapping("/mypage/order-payment")
    public String orderPayment() {return "mypage/order-payment";}

    @GetMapping("/cus_center_asview")
    public String cus_center_asview() {
        return "customer_center/cus_center_asview";    }

    @GetMapping("/cus_center_cancel_refund")
    public String cus_center_cancel_refund() {
        return "customer_center/cus_center_cancel_refund";    }

    @GetMapping("/cus_center_delivery")
    public String cus_center_delivery() {
        return "customer_center/cus_center_delivery";    }

    @GetMapping("/cus_center_member_info")
    public String cus_center_member_info() {
        return "customer_center/cus_center_member_info";    }

    @GetMapping("/cus_center_member_service")
    public String cus_center_member_service() {
        return "customer_center/cus_center_member_service";    }

    @GetMapping("/cus_center_notice")
    public String cus_center_notice() {
        return "customer_center/cus_center_notice";    }

    @GetMapping("/cus_center_order_file")
    public String cus_center_order_file() {
        return "customer_center/cus_center_order_file";    }

    @GetMapping("/cus_center_refund_service")
    public String cus_center_refund_service() {
        return "customer_center/cus_center_refund_service";    }
}
