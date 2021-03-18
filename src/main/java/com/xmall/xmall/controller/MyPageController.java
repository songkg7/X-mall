package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Order;
import com.xmall.xmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final OrderRepository orderRepository;

    // A/S 접수 안내
    @GetMapping("/as_infoguide")
    public String as_infoguide(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);

        return "mypage/as_infoguide";
    }

    // 쿠폰
    @GetMapping("/coupon")
    public String coupon(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);

        return "mypage/coupon";
    }

    // 주문배송
    @GetMapping("/order_delivery")
    public String order_delivery(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);

        return "mypage/order_delivery";
    }

    // 취소/반품
    @GetMapping("/return_cancle")
    public String oreturn_cancle(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);

        return "mypage/return_cancle";
    }

    // 최근 주문 내역     *url 로 접속해야 함
    @GetMapping("/my_page")
    public String side_mypage(@CurrentAccount Account account, Model model) {

        // FIXME: join table 해서 특정사용자의 주문만 가져올 수 있도록 수정
//        account.getId();
        List<Order> orderLists = orderRepository.findAll();

        model.addAttribute(account);
        model.addAttribute("orderLists", orderLists);

        return "mypage/side_mypage";
    }
    // 비밀번호 변경
    @GetMapping("/pwd_change")
    public String pwd_change() {
        return "mypage/pwd_change";
    }
    
    // 회원 탈퇴
    @GetMapping("/withdrawal")
    public String withdrawal() {
        return "mypage/withdrawal";
    }
}
