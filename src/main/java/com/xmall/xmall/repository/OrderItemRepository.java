package com.xmall.xmall.repository;


import com.xmall.xmall.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemRepositoryExtension {
}
