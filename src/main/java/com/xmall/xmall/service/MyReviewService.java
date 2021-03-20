package com.xmall.xmall.service;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.MyReviewRepository;

import com.xmall.xmall.review.My_Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyReviewService {

    private final MyReviewRepository myReviewRepository;


    @Transactional
    public void create(Account account, String subject, String mainText){
        My_Review my_review = My_Review.createReview(account, subject,mainText);

        myReviewRepository.save(my_review);
    }




}
