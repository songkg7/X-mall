package com.xmall.xmall.repository;

import com.xmall.xmall.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Account findByNickname(String nickname);

//    @Query(value = "select a.joined_at, count(*) from account a where a.joined_at between date_trunc('week', current_date)\\:\\:date - 1 and date_trunc('week', current_date)\\:\\:date + 2 group by a.joined_at order by a.joined_at", nativeQuery = true)
//    @Query(value = "select count(*) from account", nativeQuery = true)
//    List<Account> findAllPerDay();
}
