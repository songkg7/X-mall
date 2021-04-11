package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.MyReview;
import com.xmall.xmall.domain.Order;
import com.xmall.xmall.domain.OrderItem;
import com.xmall.xmall.form.CheckPwdForm;
import com.xmall.xmall.form.ReviewCreateForm;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.repository.MyReviewRepository;
import com.xmall.xmall.repository.OrderItemRepository;
import com.xmall.xmall.repository.OrderRepository;
import com.xmall.xmall.service.MyReviewService;
import lombok.RequiredArgsConstructor;
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
    private final OrderItemRepository orderItemRepository;



    // 최근 주문 내역     *url 로 접속해야 함
    @GetMapping("/myPage/side_mypage")
    public String side_mypage(@CurrentAccount Account account, Model model) {
        // 계정을 모델에 담고
        model.addAttribute(account);

        // 주문리스트 리포지토리에서 내계정에 관련된 주문 리스트를 배열로 가져온다.
        List<Order> orderLists = orderRepository.findByAccount(account);
        model.addAttribute("orderLists", orderLists);

        List<MyReview> reviewLists = myReviewRepository.findByAccount(account);
        model.addAttribute("reviewLists",reviewLists);
        return "myPage/side_mypage";
    }

    // 리뷰 작성
    @GetMapping("/myPage/my_createForm/{orderItemId}/create")
    public String reviewCreateProc(@CurrentAccount Account account, @PathVariable("orderItemId") Long orderItemId, Model model){
        OrderItem orderItem = orderItemRepository.findById(orderItemId).get();

        model.addAttribute(account);
        model.addAttribute(orderItem);
        ReviewCreateForm reviewCreateForm = new ReviewCreateForm();
        reviewCreateForm.setItemName(orderItem.getItem().getName());
        reviewCreateForm.setOrderItemSize(orderItem.getOrderItemSize());

        model.addAttribute("reviewCreateForm", reviewCreateForm);

        return "myPage/my_createForm";
    }

    @PostMapping("/myPage/my_createForm/{orderItemId}/create")
//    public String reviewCreateForm(@CurrentAccount Account account, @PathVariable("orderItemId") Long orderItemId, @Valid ReviewCreateForm form, Errors errors, Model model) {
    public String reviewCreateForm(@Valid ReviewCreateForm reviewCreateForm, @CurrentAccount Account account, @PathVariable("orderItemId") Long orderItemId, Errors errors, Model model) {
////        Item item = itemRepository.findById(itemId).get();
        OrderItem orderItem = orderItemRepository.findById(orderItemId).get();
        model.addAttribute(orderItem);

        // FIXME: form 만 파라미터로 전달한 후 서비스에서 분해하자
        myReviewService.create(
                 account,
                 reviewCreateForm.getSubject(),
                 reviewCreateForm.getMainText(),
                 reviewCreateForm.getItemName(),
                 reviewCreateForm.getOrderItemSize(),
                 reviewCreateForm.getStarRate(),
                 reviewCreateForm.getStarBlack(),
                 reviewCreateForm.getStarGray()
        );

        return "redirect:/myPage/side_mypage";
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

}
