package com.xmall.xmall.controller;

import com.xmall.xmall.domain.Item;
import com.xmall.xmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemRepository itemRepository;

    // FIXME: order-payment url 적절하지 않음
    @GetMapping("/order-payment/{id}")
    // FIXME: @RequestParam 을 쓰는 시도는 좋았으나 get 요청으로 url 로 직접 데이터를 넘기면
    //  어노테이션으로 데이터를 받지 않고 다이렉트로 받아야합니다. 어노테이션으로 받고 싶다면 POST 요청으로
    //  데이터를 보내도록 해야합니다.
    public String orderPayment(@PathVariable Long id, Model model, @RequestParam("amount") int amount) {
        Item item = itemRepository.findById(id).get();

        if (item == null) {
            return "error";
        }

        model.addAttribute("item", item);
        model.addAttribute("amount", amount);

        return "mypage/order-payment";
    }
}
