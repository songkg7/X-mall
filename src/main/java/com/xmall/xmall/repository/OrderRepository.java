package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByAccount(Account account);
}
