package com.xmall.xmall.service;

import com.xmall.xmall.SignUpForm;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    public void signUp(SignUpForm signUpForm) {

        Account account = new Account();
        account.setEmail(signUpForm.getEmail());
        account.setPassword(signUpForm.getPassword());

        accountRepository.save(account);

        log.info(account.getEmail());
    }

}
