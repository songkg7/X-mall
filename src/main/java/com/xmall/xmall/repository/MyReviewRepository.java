package com.xmall.xmall.repository;

import com.xmall.xmall.domain.MyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface MyReviewRepository extends JpaRepository<MyReview, Long> {
}
