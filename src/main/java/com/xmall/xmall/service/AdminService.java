package com.xmall.xmall.service;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Order;
import com.xmall.xmall.domain.OrderItem;
import com.xmall.xmall.repository.AccountRepository;
import com.xmall.xmall.repository.ItemRepository;
import com.xmall.xmall.repository.OrderItemRepository;
import com.xmall.xmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;


    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    public int getTotalSales() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        int sales = 0;
        for (OrderItem orderItem : orderItems) {
            sales += orderItem.getTotalPrice();
        }
        return sales;
    }
}
