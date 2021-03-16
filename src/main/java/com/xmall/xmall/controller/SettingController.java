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

//    @GetMapping("/cs")
//    public String cs() {
//        return ""
//    }

}
