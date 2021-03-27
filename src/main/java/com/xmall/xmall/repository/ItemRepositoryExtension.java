package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ItemRepositoryExtension {

    Page<Item> findByKeyword(String keyword, Pageable pageable);
}
