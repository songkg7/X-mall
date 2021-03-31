package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Order;

import java.util.List;

public interface OrderItemRepositoryExtension {

    List<Integer> findSalesPerDay();

    List<Order> findAllOrdersInfo();

    List<Long> findOrdersPerDay();

}
