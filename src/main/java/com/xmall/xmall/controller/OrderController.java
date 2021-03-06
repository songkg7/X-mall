package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.form.OrderForm;
import com.xmall.xmall.repository.CartRepository;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.repository.OrderRepository;
import com.xmall.xmall.service.AccountService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemRepository itemRepository;
    private final OrderService orderService;
    private final AccountService accountService;

    // 바로 구매로 상품 하나 구매
    @PostMapping("/order/{id}")
    public String orderPayment(@CurrentAccount Account account,
                               @PathVariable Long id, Model model,
                               OrderForm orderForm, RedirectAttributes attributes) {

        if (!accountService.checkEmailVerified(account)) {
            attributes.addFlashAttribute("message", "이메일 인증 후 이용해주세요.");
            return "redirect:/";
        }

        model.addAttribute(account);

        // 선택한 상품 갯수, 상품 사이즈 전달
        model.addAttribute("orderForm", orderForm);
        // 바로구매로 진입하면 선택한 아이템 찾아오기
        Optional<Item> byId = itemRepository.findById(id);
        byId.ifPresent(item -> model.addAttribute("item", item));
        return "order/payment_order";
    }

    // 주문페이지에서는 본인이 선택한 물건들이 맞는지 확인만 하게 된다.
    @PostMapping("/order/payment")
    public String orderPaymentProcess(@CurrentAccount Account account,
                                      @RequestParam Long itemId,
                                      @Valid OrderForm orderForm, Errors errors, Model model) {

        if (errors.hasErrors()) {
            Item item = itemRepository.findById(itemId).orElseThrow();
            model.addAttribute("item", item);
            model.addAttribute(account);
            model.addAttribute("error", "유효성 체크를 통과하지 못했습니다.");
            model.addAttribute("orderForm", orderForm);
            return "order/payment_order";
        }

        Item item = itemRepository.findById(itemId).orElseThrow();

        orderService.order(item, account, orderForm);
        return "redirect:/myPage/side_mypage";
    }

    @GetMapping("1")
    public String test() {
        return "error-test";
    }

    @PostMapping("order/{id}/cancel")
    public String cancelOrder(@CurrentAccount Account account, @PathVariable("id") Long orderId, Model model){
        model.addAttribute(account);
        orderService.cancelOrder(orderId);
        return "redirect:/myPage/side_mypage";
    }


}
