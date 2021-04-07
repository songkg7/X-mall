package com.xmall.xmall.service;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.MyReview;
import com.xmall.xmall.repository.MyReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MyReviewService {

    private final MyReviewRepository myReviewRepository;

    @Transactional
    public void create(Account account, String subject, String mainText, String itemName, String orderItemSize, String starRate,String starBlack, String starGray){
        MyReview myReview = MyReview.createReview(account, subject, mainText, itemName, orderItemSize, starRate, starBlack, starGray);
        myReviewRepository.save(myReview);


    }




}
