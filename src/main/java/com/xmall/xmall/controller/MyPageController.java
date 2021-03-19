package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.form.CheckPwdForm;
import com.xmall.xmall.service.AccountService;
import com.xmall.xmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

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
        model.addAttribute(account);

        return "mypage/side_mypage";
    }
    // 비밀번호 변경
    @GetMapping("/pwd_change")
    public String pwd_change(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new CheckPwdForm());

        return "mypage/pwd_change";
    }

    @PostMapping("/pwd_change/edit")
    public String pwd_changeEdit(@CurrentAccount Account account, Model model, CheckPwdForm checkPwdForm) {
        model.addAttribute(account);
        boolean resultPwdCheck = passwordEncoder.matches(checkPwdForm.getCurrent_pwd(), account.getPassword());

        if (resultPwdCheck == false) {
            return "error";
        }

        accountService.update(account.getNickname(), checkPwdForm);

        return "redirect:/";
    }
    
    // 회원 탈퇴
    @GetMapping("/withdrawal")
    public String withdrawal(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new CheckPwdForm());

        return "mypage/withdrawal";
    }

    @PostMapping("/withdrawal/delete")
    public String withdrawalDelete(@CurrentAccount Account account, Model model, CheckPwdForm checkPwdForm) {
        model.addAttribute(account);
        boolean resultPwdCheck = passwordEncoder.matches(checkPwdForm.getCurrent_pwd(), account.getPassword());

        if (resultPwdCheck == false) {
            return "error";
        }

        accountService.delete(account.getNickname());

        return "redirect:/";
    }
}
