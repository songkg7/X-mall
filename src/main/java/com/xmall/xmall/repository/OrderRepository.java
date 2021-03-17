package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
