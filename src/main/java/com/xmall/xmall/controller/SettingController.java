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
@RequiredArgsConstructor
public class SettingController {

    private final ItemRepository itemRepository;

    // FIXME: order-payment url 적절하지 않음
    @GetMapping("/order-payment/{id}")
    public String orderPayment(@PathVariable Long id, Model model, @RequestParam("amount") int amount) {
        Item item = itemRepository.findById(id).get();

        if (item == null) {
            return "error";
        }

        model.addAttribute("item", item);
        model.addAttribute("amount", amount);

        return "mypage/order-payment";
    }

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

    @GetMapping("/cus_center_main")
    public String cus_center_main() {
        return "customer_center/cus_center_main";    }

    @GetMapping("/as_infoguide")
    public String as_infoguide() {
        return "mypage/as_infoguide";    }

<<<<<<< HEAD
    @GetMapping("/coupon")
    public String coupon() {
        return "mypage/coupon";    }

    @GetMapping("/order_delivery")
    public String order_delivery() {
        return "mypage/order_delivery";    }

    @GetMapping("/return_cancle")
    public String oreturn_cancle() {
        return "mypage/return_cancle";    }

    @GetMapping("/side_mypage")
    public String side_mypage() {
        return "mypage/side_mypage";    }
=======

>>>>>>> 639f4acb97891e994c03eb3a86fb15e08097d893
}
