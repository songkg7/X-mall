package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.*;
import com.xmall.xmall.form.OrderForm;
import com.xmall.xmall.repository.CartRepository;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.repository.OrderRepository;
import com.xmall.xmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderService orderService;

    @GetMapping("/order/{id}")
    // FIXME: @RequestParam
    public String orderPayment(@CurrentAccount Account account,
                               @PathVariable Long id, Model model, int amount,
                               OrderForm orderForm) {
        Item item = itemRepository.findById(id).get();

        model.addAttribute(account);
        model.addAttribute(orderForm);
        model.addAttribute("item", item);
        model.addAttribute("amount", amount);

        // FIXME : 파일 경로 수정하기
        return "order/payment";
    }

    @PostMapping("/order/payment")
    public String orderPaymentProcess(@Valid OrderForm orderForm, Errors errors, @CurrentAccount Account account) {

        Item item = itemRepository.findByName(orderForm.getItemName());

        orderService.order(item, orderForm, account);

        // FIXME: 주문 완료 페이지로 가기
        return "redirect:/";
    }

    @GetMapping("1")
    public String test() {
        return "mypage/order-payment";
    }

}
