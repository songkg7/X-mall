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

    @GetMapping("/order/{id}")
    public String orderPayment(@CurrentAccount Account account,
                               @PathVariable Long id, Model model, int amount) {
        Item item = itemRepository.findById(id).get();

        // TODO: 배송지 정보 받아오기
        OrderForm orderForm = new OrderForm(); // 주문서 작성
        orderForm.setAmount(amount); // 주문 수량 미리 설정
        orderForm.setItemName(item.getName());
        orderForm.setPrice(item.getPrice());

        model.addAttribute(account);
        model.addAttribute(orderForm);
        model.addAttribute("item", item);

        return "order/payment";
    }

    // 주문페이지에서는 본인이 선택한 물건들이 맞는지 확인만 하게 된다.
    @PostMapping("/order/payment/{id}")
    public String orderPaymentProcess(@CurrentAccount Account account,
                                      @PathVariable Long id,
                                      @Valid OrderForm orderForm,
                                      Errors errors) {
        log.info(String.valueOf(orderForm.getPrice()));

        Optional<Item> byId = itemRepository.findById(id);
        byId.ifPresent(item -> orderService.order(item, orderForm, account));

        // FIXME: 주문 완료 페이지로 가기
        return "redirect:/";
    }

    @GetMapping("1")
    public String test() {
        return "order/payment";
    }

}
