package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
