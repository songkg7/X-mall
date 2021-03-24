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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderService orderService;

    // 바로 구매로 상품 하나 구매
//    @PostMapping("/order/{id}")
//    public String orderPayment(@CurrentAccount Account account,
//                               @PathVariable Long id, Model model,
//                               @RequestParam int amount) {
//
//        model.addAttribute(account);
//
//        // 선택한 상품 갯수 전달
//        model.addAttribute("amount", amount);
//        // 바로구매로 진입하면 선택한 아이템 찾아오기
//        Optional<Item> byId = itemRepository.findById(id);
//        byId.ifPresent(item -> model.addAttribute("item", item));
//        // TODO: 배송지 정보 받아오기
//        return "order/payment-test";
//    }

    // 바로 구매로 상품 하나 구매
    @PostMapping("/order/{id}")
    public String orderPayment(@CurrentAccount Account account,
                               @PathVariable Long id, Model model,
                               OrderForm orderForm) {

        model.addAttribute(account);

        // 선택한 상품 갯수, 상품 사이즈 전달
        model.addAttribute("orderForm", orderForm);
        // 바로구매로 진입하면 선택한 아이템 찾아오기
        Optional<Item> byId = itemRepository.findById(id);
        byId.ifPresent(item -> model.addAttribute("item", item));
        // TODO: 배송지 정보 받아오기
        return "order/payment";
    }

    // 주문페이지에서는 본인이 선택한 물건들이 맞는지 확인만 하게 된다.
//    @PostMapping("/order/payment")
//    public String orderPaymentProcess(@CurrentAccount Account account,
//                                      @RequestParam Long itemId,
//                                      @RequestParam int amount) {
//
//        Optional<Item> byId = itemRepository.findById(itemId);
//        byId.ifPresent(item -> orderService.order(item, account, amount));
//        return "redirect:/my_page";
//    }

    // 주문페이지에서는 본인이 선택한 물건들이 맞는지 확인만 하게 된다.
    @PostMapping("/order/payment")
    public String orderPaymentProcess(@CurrentAccount Account account,
                                      @RequestParam Long itemId,
                                      OrderForm orderForm) {

        Optional<Item> byId = itemRepository.findById(itemId);

        byId.ifPresent(item -> orderService.order(item, account, orderForm));
        return "redirect:/myPage/side_mypage";
    }

    @GetMapping("1")
    public String test() {
        return "order/payment-test2";
    }

    @PostMapping("order/{id}/cancel")
    public String cancelOrder(@CurrentAccount Account account, @PathVariable("id") Long orderId, Model model){
        model.addAttribute(account);
        orderService.cancelOrder(orderId);
        return "redirect:/myPage/side_mypage";
    }


}
