package com.xmall.xmall.service;

import com.xmall.xmall.domain.*;
import com.xmall.xmall.form.OrderForm;
import com.xmall.xmall.repository.AccountRepository;
import com.xmall.xmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.xmall.xmall.domain.Order.createOrder;
import static com.xmall.xmall.domain.OrderItem.createOrderItem;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;

    public void order(Item item, Account account, OrderForm orderForm) {
        log.info("주문한 회원 = " + account.getNickname());
        // detach 상태인 회원을 다시 찾아오면서 영속성 관리 상태로 만든다.
        Account findAccount = accountRepository.findById(account.getId()).get();

        // 신규 배송지 설정
        Address address = new Address(orderForm.getAddress(), orderForm.getDetailAddress()
                , orderForm.getExtraAddress(), orderForm.getPostcode());

        findAccount.getAddressList().add(address);

        // 주문 아이템 생성
        OrderItem orderItem = createOrderItem(item, orderForm);

        // 주문 생성
        Order order = createOrder(findAccount, address, orderForm, orderItem);

        // DB에 주문 저장
        orderRepository.save(order);
    }

    // 취소
    public void cancelOrder(Long orderId) {
        // 주문 엔테티 조회
        Optional<Order> orderList = orderRepository.findById(orderId);
        orderList.ifPresent(Order::cancel);
    }
}
