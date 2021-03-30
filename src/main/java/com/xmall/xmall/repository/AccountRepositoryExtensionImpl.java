//package com.xmall.xmall.repository;
//
//import com.xmall.xmall.domain.Account;
//import com.xmall.xmall.domain.QAccount;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import java.util.List;
//
//import static com.xmall.xmall.domain.QAccount.*;
//
//public class AccountRepositoryExtensionImpl extends QuerydslRepositorySupport implements AccountRepositoryExtension {
//
//    public AccountRepositoryExtensionImpl() {
//        super(Account.class);
//    }
//
//    @Override
//    public List<Account> findAccountPerDay() {
//        from(account)
//                .where(account.JoinedAt)
//    }
//}
