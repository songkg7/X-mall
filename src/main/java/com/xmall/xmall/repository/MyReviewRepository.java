package com.xmall.xmall.repository;

import com.xmall.xmall.review.MyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface MyReviewRepository extends JpaRepository<MyReview, Long> {
}
