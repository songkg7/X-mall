package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.form.CheckPwdForm;
import com.xmall.xmall.domain.Order;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.review.ReviewCreateForm;
import com.xmall.xmall.service.MyReviewService;
import com.xmall.xmall.review.MyReview;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.OrderRepository;
import com.xmall.xmall.repository.MyReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyReviewService myReviewService;
    private final MyReviewRepository myReviewRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;



    // 최근 주문 내역     *url 로 접속해야 함
    @GetMapping("/myPage/side_mypage")
    //현재 계정 정보를 모델에 담고
    public String side_mypage(@CurrentAccount Account account, Model model) {
        // 주문리스트 리포지토리에서 내계정에 관련된 주문 리스트를 배열로 가져온다.
        List<Order> orderLists = orderRepository.findByAccount(account);
        // 계정을 모델에 담고
        model.addAttribute(account);
        // 주문리스트를 모델에 담는다.
        model.addAttribute("orderLists", orderLists);
        // 최근주문내역 페이지에 보여준다.
        return "myPage/side_mypage";
    }

    @GetMapping("/myPage/side_myPage")
    public String reviewList(Model model){
        List<MyReview> reviewList = myReviewRepository.findAll();
        model.addAttribute("reviewLists",reviewList);
        return "myPage/side_mypage";
    }

    // 리뷰 작성
    @GetMapping("/myPage/my_createForm/{itemId}/create")
    public String reviewCreateForm(@CurrentAccount Account account, @PathVariable("itemId") Long itemId, Model model){
        Item item = itemRepository.findById(itemId).get();

        model.addAttribute(account);
        model.addAttribute(item);
        model.addAttribute(new ReviewCreateForm());

        return "myPage/my_createForm";
    }

    @PostMapping("/myPage/my_createForm/{itemId}/create")
    public String reviewCreate(@CurrentAccount Account account, @PathVariable("itemId") Long itemId, @Valid ReviewCreateForm form, Errors errors, Model model) {
        Item item = itemRepository.findById(itemId).get();
        myReviewService.create(account, form);

        model.addAttribute(item);
        model.addAttribute(account);
        return "redirect:/my_page";
    }








    // A/S 접수 안내
    @GetMapping("/myPage/as_infoguide")
    public String as_infoguide(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        return "myPage/as_infoguide";
    }

    // 쿠폰
    @GetMapping("/myPage/coupon")
    public String coupon(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        return "myPage/coupon";
    }

    // 주문배송
    @GetMapping("/myPage/order_delivery")
    public String order_delivery(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        return "myPage/order_delivery";
    }

    // 취소/반품
    @GetMapping("/myPage/return_cancle")
    public String oreturn_cancle(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        return "myPage/return_cancel";
    }

    // 비밀번호 변경
    @GetMapping("/myPage/pwd_change")
    public String pwd_change(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new CheckPwdForm());
        return "myPage/pwd_change";
    }
    
    // 회원 탈퇴
    @GetMapping("/myPage/withdrawal")
    public String withdrawal(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new CheckPwdForm());
        return "myPage/withdrawal";
    }


}
