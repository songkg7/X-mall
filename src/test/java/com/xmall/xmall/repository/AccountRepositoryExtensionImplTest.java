package com.xmall.xmall.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.domain.QAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Profile("dev")
class AccountRepositoryExtensionImplTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    @BeforeEach
    void init() {
        Account account1 = Account.builder()
                .email("test1@example.com")
                .nickname("testuser1")
                .password(passwordEncoder.encode("12345678"))
                .name("testuser1")
                .phone("01011111111")
                .build();

        account1.setJoinedAt(LocalDateTime.of(2021, 3, 28, 0, 0));

        accountRepository.save(account1);

        Account account2 = Account.builder()
                .email("test2@example.com")
                .nickname("testuser2")
                .password(passwordEncoder.encode("12345678"))
                .name("testuser2")
                .phone("01011111111")
                .build();

        account2.setJoinedAt(LocalDateTime.of(2021, 3, 29, 0, 0));

        accountRepository.save(account2);

        Account account3 = Account.builder()
                .email("test3@example.com")
                .nickname("testuser3")
                .password(passwordEncoder.encode("12345678"))
                .name("testuser3")
                .phone("01011111111")
                .build();

        account3.setJoinedAt(LocalDateTime.of(2021, 3, 26, 0, 0));

        accountRepository.save(account3);

        Account account4 = Account.builder()
                .email("test4@example.com")
                .nickname("testuser4")
                .password(passwordEncoder.encode("12345678"))
                .name("testuser4")
                .phone("01011111111")
                .build();

        account4.setJoinedAt(LocalDateTime.of(2021, 3, 20, 0, 0));

        accountRepository.save(account4);
    }

    @Test
    @DisplayName("기본 테스트")
    void basic() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Account findAccount = queryFactory
                .select(QAccount.account)
                .from(QAccount.account)
                .where(QAccount.account.name.eq("관리자"))
                .fetchOne();

        System.out.println("findAccount = " + findAccount.getName());

        assertEquals(findAccount.getName(), "관리자");

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

    @Test
    @DisplayName("요일별 회원 가입 인원수 가져오기")
    void findSignUpAccountPerDay() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        String date = sdf.format(new Date());
        System.out.println(getWeekOfYear(date));

        int week = getWeekOfYear(date);

        List<Tuple> results = queryFactory
                .select(QAccount.account.JoinedAt, QAccount.account.JoinedAt.count())
                .from(QAccount.account)
//                .where(QAccount.account.JoinedAt.before(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)))
//                .where(QAccount.account.JoinedAt
//                        .between(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).minusDays(10),
//                                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).plusDays(2)))
                .where(QAccount.account.JoinedAt
                .between(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                        .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week - 1)
                        .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)),
                        LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)))
                .groupBy(QAccount.account.JoinedAt)
                .orderBy(QAccount.account.JoinedAt.asc())
                .fetch();

        System.out.println("results.size() = " + results.size());



        for (Tuple result : results) {
            System.out.println("result = " + result);
        }

    }

    @Test
    @DisplayName("전체 회원 조회")
    void findAllAccount() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Account> result = queryFactory
                .select(QAccount.account)
                .from(QAccount.account)
                .fetch();

        for (Account account : result) {
            System.out.println("account = " + account.getName());
        }

    }

}