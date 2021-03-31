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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
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

    JPAQueryFactory queryFactory;

    @BeforeEach
    void init() {
        queryFactory = new JPAQueryFactory(em);

        createAccount("test1@example.com", "testuser1", 28);

        createAccount("test2@example.com", "testuser2", 29);

        createAccount("test3@example.com", "testuser3", 26);

        createAccount("test4@example.com", "testuser4", 20);
    }

    @Test
    @DisplayName("회원 검색 테스트")
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

        assertEquals(result.size(), accountRepository.count());

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