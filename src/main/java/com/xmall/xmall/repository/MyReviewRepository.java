package com.xmall.xmall.repository;

import com.xmall.xmall.review.My_Review;
import com.xmall.xmall.review.My_Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface MyReviewRepository extends JpaRepository<My_Review, Long> {
}
