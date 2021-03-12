package com.xmall.xmall.service;

import com.xmall.xmall.AccountForm;
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

    // 회원가입
    public void signUp(SignUpForm signUpForm) {

        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();

//        Account account = new Account();
//
//        account.setEmail(signUpForm.getEmail());

//        String encodePwd = passwordEncoder.encode(signUpForm.getPassword());
//        account.setPassword(encodePwd);

        accountRepository.save(account);

        // TODO: email 보내기
    }

    // 로그인
    public boolean login(AccountForm accountForm, Account account) {

        String rawPassword = accountForm.getPassword(); // raw value
        String encodedPassword = account.getPassword(); // encoded value

        return passwordEncoder.matches(rawPassword, encodedPassword);

    }
}
