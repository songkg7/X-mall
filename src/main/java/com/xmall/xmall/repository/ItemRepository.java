package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
