package com.xmall.xmall.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xmall.xmall.domain.*;
import com.xmall.xmall.form.OrderForm;
import com.xmall.xmall.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.xmall.xmall.domain.QOrder.*;
import static com.xmall.xmall.domain.QOrderItem.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderItemRepositoryExtensionImplTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderService orderService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void init() {
        queryFactory = new JPAQueryFactory(em);

        createAccount("test1@example.com", "testuser1", 28);

        createAccount("test2@example.com", "testuser2", 29);

        createAccount("test3@example.com", "testuser3", 26);

        createAccount("test4@example.com", "testuser4", 20);

        createItemAndOrder("testItem1");
        createItemAndOrder("testItem2");
    }

    @Test
    @DisplayName("요일별 매출 가져오기")
    void salesPerDay() {

        // given


        queryFactory = new JPAQueryFactory(em);
        String date = sdf.format(new Date());
        int week = getWeekOfYear(date);

        List<Integer> results = queryFactory
                .select(orderItem.amount.multiply(orderItem.orderPrice).sum())
                .from(order)
                .where(order.status.eq(OrderStatus.ORDER).and(order.orderDate.between(
                        LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week - 1)
                                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)),
                        LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.SECONDS))))
                .leftJoin(orderItem).on(orderItem.order.id.eq(order.id)).fetchJoin()
                .groupBy(order.orderDate.dayOfWeek())
                .orderBy(order.orderDate.dayOfWeek().asc())
                .fetch();

//        List<Integer> result2 = queryFactory
//                .select(order.orderDate.yearWeek())
//                .from(order).fetch();

        List<Integer> result2 = queryFactory
                .select(order.orderDate.yearWeek())
                .from(order).fetch();

        System.out.println("results.size() = " + results.size());
        for (Integer result : results) {
            System.out.println("result = " + result);
        }

        for (Integer integer : result2) {
            System.out.println("integer = " + integer);
        }

    }

    @Test
    @DisplayName("종합 주문 정보")
    void allOrderInfo() {

        List<Order> fetch = queryFactory
                .select(order)
                .from(order)
                .leftJoin(orderItem).on(order.id.eq(orderItem.order.id)).fetchJoin()
                .leftJoin(QAccount.account).on(order.account.id.eq(QAccount.account.id)).fetchJoin()
                .leftJoin(QItem.item).on(orderItem.item.id.eq(QItem.item.id)).fetchJoin()
                .fetch();

        for (Order fetch1 : fetch) {
            System.out.println("fetch1 = " + fetch1.getOrderItems().get(0).getOrderPrice());
        }

    }

    private void createItemAndOrder(String itemName) {
        Account account = accountRepository.findByEmail("test1@example.com");
        Item item = Item.builder()
                .description("sadf")
                .stockQuantity(10)
                .subTitle("Asdf")
                .name(itemName)
                .genderType("men")
                .price(10000)
                .createdAt(LocalDateTime.now().minusDays(1))
                .build();
        itemRepository.save(item);

        OrderForm orderForm = new OrderForm();
        orderForm.setPrice(item.getPrice());
        orderForm.setAmount(5);
        orderForm.setOrderItemSize("100");

        orderService.order(item, account, orderForm);
    }


    private void createAccount(String email, String testUser, int day) {
        Account account = Account.builder()
                .email(email)
                .nickname(testUser)
                .password(passwordEncoder.encode("12345678"))
                .name(testUser)
                .phone("01011111111")
                .build();

        account.setJoinedAt(LocalDateTime.of(2021, 3, day, 0, 0));

        accountRepository.save(account);
    }

    int getWeekOfYear(String date) {
        Calendar cal = Calendar.getInstance();
        String[] dates = date.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);
        cal.set(year, month - 1, day);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

}