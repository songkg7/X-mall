package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ItemRepositoryExtension {

    List<Item> findByKeyword(String keyword);
}
