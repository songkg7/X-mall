package com.xmall.xmall.repository;

import com.xmall.xmall.domain.OrderItem;
import com.xmall.xmall.domain.OrderStatus;
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

import static com.xmall.xmall.domain.QOrder.*;
import static com.xmall.xmall.domain.QOrderItem.*;

public class OrderItemRepositoryExtensionImpl extends QuerydslRepositorySupport implements OrderItemRepositoryExtension {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    public OrderItemRepositoryExtensionImpl() {
        super(OrderItem.class);
    }

    @Override
    public List<Integer> findSalesPerDay() {

        // 날짜 계산
        String date = sdf.format(new Date());
        int week = getWeekOfYear(date);

        // FIXME: querydsl yearWeek() 를 사용하면 날짜계산 로직이 없어도 주차를 가져올 수 있다.

        return from(order)
                .where(order.status.eq(OrderStatus.ORDER).and(order.orderDate.between(
                        LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week - 1)
                                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)),
                        LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))))
                .leftJoin(orderItem).on(orderItem.order.id.eq(order.id)).fetchJoin()
                .groupBy(order.orderDate)
                .orderBy(order.orderDate.asc())
                .select((orderItem.amount.multiply(orderItem.orderPrice)).sum())
                .fetch();
    }

    // 현재 날짜를 바탕으로 몇주차인지 알아내기 위한 로직
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
