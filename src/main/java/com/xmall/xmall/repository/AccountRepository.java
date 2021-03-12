package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
