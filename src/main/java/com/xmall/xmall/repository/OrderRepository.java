package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByAccount(Account account);
}
