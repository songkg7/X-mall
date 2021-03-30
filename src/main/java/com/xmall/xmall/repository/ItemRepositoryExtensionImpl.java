package com.xmall.xmall.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.xmall.xmall.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.xmall.xmall.domain.QItem.*;
import static com.xmall.xmall.domain.QOrderItem.*;

public class ItemRepositoryExtensionImpl extends QuerydslRepositorySupport implements ItemRepositoryExtension {
    
    public ItemRepositoryExtensionImpl() {
        super(Item.class);
    }

    @Override
    public Page<Item> findByKeyword(String keyword, Pageable pageable) {
        JPQLQuery<Item> query = from(item)
                .where(item.name.containsIgnoreCase(keyword)
                        .or(item.subTitle.containsIgnoreCase(keyword)));

        JPQLQuery<Item> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Item> queryResults = pageableQuery.fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public Long findByBestItem() {
        Tuple tuple = from(orderItem)
                .groupBy(orderItem.item.id)
                .select(orderItem.item.id, orderItem.amount.sum())
                .orderBy(orderItem.amount.sum().desc())
                .fetchFirst();
        return tuple.get(orderItem.item.id);

    }

}
