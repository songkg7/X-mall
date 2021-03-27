package com.xmall.xmall.repository;

import com.querydsl.jpa.JPQLQuery;
import com.xmall.xmall.domain.Item;
import com.xmall.xmall.domain.QItem;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.xmall.xmall.domain.QItem.*;

public class ItemRepositoryExtensionImpl extends QuerydslRepositorySupport implements ItemRepositoryExtension {
    
    public ItemRepositoryExtensionImpl() {
        super(Item.class);
    }

    @Override
    public List<Item> findByKeyword(String keyword) {
        JPQLQuery<Item> query = from(item)
                .where(item.name.containsIgnoreCase(keyword)
                        .or(item.subTitle.containsIgnoreCase(keyword)));

        return query.fetch();
    }
}
