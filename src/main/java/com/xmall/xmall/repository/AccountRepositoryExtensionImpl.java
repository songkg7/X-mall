package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Account;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

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

import static com.xmall.xmall.domain.QAccount.*;

public class AccountRepositoryExtensionImpl extends QuerydslRepositorySupport implements AccountRepositoryExtension {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);


    public AccountRepositoryExtensionImpl() {
        super(Account.class);
    }

    @Override
    public List<Long> findAccountPerDay() {
        // 날짜 계산
        String date = sdf.format(new Date());
        int week = getWeekOfYear(date);

        return from(account)
                .where(account.JoinedAt.between(
                        LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week - 1)
                                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)),
                        LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)))
                .groupBy(account.JoinedAt)
                .orderBy(account.JoinedAt.asc())
                .select(account.JoinedAt.count())
                .fetch();
    }

    // 현재 날짜를 바탕으로 몇주차인지 알아내기 위한 로직
    // FIXME: refactor
    private int getWeekOfYear(String date) {
        Calendar cal = Calendar.getInstance();
        String[] dates = date.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);
        cal.set(year, month - 1, day);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
}
