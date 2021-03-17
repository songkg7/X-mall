package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
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

    @GetMapping("/order-payment/{id}")
    // FIXME: @RequestParam
    public String orderPayment(@CurrentAccount Account account, @PathVariable Long id, Model model, @RequestParam("amount") int amount) {
        Item item = itemRepository.findById(id).get();

        model.addAttribute(account);
        model.addAttribute("item", item);
        model.addAttribute("amount", amount);

        // FIXME : 파일 경로 수정하기
        return "mypage/order-payment";
    }

}
