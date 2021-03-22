package com.xmall.xmall.service;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.MyReviewRepository;

import com.xmall.xmall.review.MyReview;
import com.xmall.xmall.review.ReviewCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MyReviewService {

    private final MyReviewRepository myReviewRepository;

    public void create(Account account, ReviewCreateForm form){
        MyReview myReview = MyReview.createReview(account, form);
        myReviewRepository.save(myReview);
    }




}
