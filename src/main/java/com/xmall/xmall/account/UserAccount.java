package com.xmall.xmall.account;

import com.xmall.xmall.domain.Account;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

    private final Account account;

    public UserAccount(Account account) {
        super(account.getNickname(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;
    }

    public UserAccount(Account account, String authority) {
        super(account.getNickname(), account.getPassword(), List.of(new SimpleGrantedAuthority(authority)));
        this.account = account;
    }
}
