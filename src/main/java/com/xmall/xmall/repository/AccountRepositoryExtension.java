package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AccountRepositoryExtension {

    List<Account> findAccountPerDay();

}
