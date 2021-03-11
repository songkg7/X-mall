package com.xmall.xmall.service;

import com.xmall.xmall.SignUpForm;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("회원가입 - 정상")
    void signUp() throws Exception {

        // 회원 데이터를 직접 입력
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("songkg7@naver.com");
        signUpForm.setPassword("12345678");

        // 회원 저장
        accountService.signUp(signUpForm);

        // then
        Account findAccount = accountRepository.findByEmail("songkg7@naver.com");

        assertEquals(findAccount.getEmail(), "songkg7@naver.com");
        assertNotNull(findAccount);

    }

    /*
    @Test
    @DisplayName("회원가입 - 비밀번호 오류")
    void signUp_error() throws Exception {

        Account account = new Account();
        account.setEmail("songkg7@gmail.com");
        account.setPassword("123");

        accountRepository.save(account);

    }
    */


}