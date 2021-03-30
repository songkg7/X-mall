package com.xmall.xmall.repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AccountRepositoryExtension {

    List<Long> findAccountPerDay();

}
