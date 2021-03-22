package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.form.CheckPwdForm;
import com.xmall.xmall.domain.Order;
import com.xmall.xmall.review.ReviewCreateForm;
import com.xmall.xmall.service.MyReviewService;
import com.xmall.xmall.review.My_Review;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.OrderRepository;
import com.xmall.xmall.repository.MyReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyReviewService myReviewService;
    private final MyReviewRepository myReviewRepository;
    private final OrderRepository orderRepository;


    // 최근 주문 내역     *url 로 접속해야 함
    @GetMapping("/my_page")
    //현재 계정 정보를 모델에 담고
    public String side_mypage(@CurrentAccount Account account, Model model) {
        // 주문리스트 리포지토리에서 내계정에 관련된 주문 리스트를 배열로 가져온다.
        List<Order> orderLists = orderRepository.findByAccount(account);
        // 계정을 모델에 담고
        model.addAttribute(account);
        // 주문리스트를 모델에 담는다.
        model.addAttribute("orderLists", orderLists);
        // 최근주문내역 페이지에 보여준다.
        return "mypage/side_mypage";
    }

    @GetMapping("/side_mypage")
    public String reviewList(Model model){
        List<My_Review> reviewList = myReviewRepository.findAll();
        model.addAttribute("reviewLists",reviewList);
        return "mypage/side_mypage";
    }


    @GetMapping("/my_createForm")
    public String my_createProc(@CurrentAccount Account account, Model model){
        List<Order> orderLists = orderRepository.findByAccount(account);
        model.addAttribute(account);
        model.addAttribute("orderLists", orderLists);
        model.addAttribute("form",new ReviewCreateForm());
        return "mypage/my_createForm";
    }


    @PostMapping("/my_createForm")
    public String my_createForm(@Valid ReviewCreateForm form, @CurrentAccount Account account) {
        myReviewService.create(account, form.getSubject(),form.getMainText());
        return "redirect:/mypage/side_mypage";
    }







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
        return "return_cancel";
    }

    // 비밀번호 변경
    @GetMapping("/pwd_change")
    public String pwd_change(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new CheckPwdForm());
        return "mypage/pwd_change";
    }
    
    // 회원 탈퇴
    @GetMapping("/withdrawal")
    public String withdrawal(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new CheckPwdForm());
        return "mypage/withdrawal";
    }


}
