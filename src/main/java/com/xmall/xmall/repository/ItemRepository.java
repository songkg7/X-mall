package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ItemRepository extends JpaRepository<Item, Long> {
}
