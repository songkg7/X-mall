package com.xmall.xmall.service;

import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.domain.Order;
import com.xmall.xmall.domain.OrderItem;
import com.xmall.xmall.form.OrderForm;
import com.xmall.xmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.xmall.xmall.domain.Order.createOrder;
import static com.xmall.xmall.domain.OrderItem.createOrderItem;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void order(Item item, Account account, int amount) {

        // 주문 아이템 생성
        OrderItem orderItem = createOrderItem(item, item.getPrice(), amount);

        // 주문 생성
        Order order = createOrder(account, orderItem);

        orderRepository.save(order);
    }
}
