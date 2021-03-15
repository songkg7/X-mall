package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
