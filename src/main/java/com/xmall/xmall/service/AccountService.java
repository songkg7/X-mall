package com.xmall.xmall.service;

import com.xmall.xmall.SignUpForm;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpForm signUpForm) {

        Account account = new Account();
        account.setEmail(signUpForm.getEmail());

        String encodePwd = passwordEncoder.encode(signUpForm.getPassword());
        account.setPassword(encodePwd);

        accountRepository.save(account);

        log.info(account.getEmail());
    }

}
