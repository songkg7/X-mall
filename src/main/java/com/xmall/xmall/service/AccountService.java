package com.xmall.xmall.service;

import com.xmall.xmall.AccountForm;
import com.xmall.xmall.SignUpForm;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

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

        // 회원 저장 후 이메일을 보내주기 위해 정보를 가져온다
        Account newAccount = accountRepository.save(account);

        // TODO: email 보내기
        // - 가짜 객체(ConsoleMailSender)를 만들어서 토큰을 콘솔에 출력, 추후 진짜 메일로 바꾼다.

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        newAccount.generateEmailCheckToken();

        // 이메일 작성
        mailMessage.setSubject("X-mall 회원가입 인증");
        mailMessage.setTo(newAccount.getEmail());
        mailMessage.setText("/check-email-token?token=" + newAccount.getEmailCheckToken()
                + "&email=" + newAccount.getEmail());

        // 메일 보내기
        javaMailSender.send(mailMessage);

    }

    // 로그인
    public boolean login(AccountForm accountForm, Account account) {

        String rawPassword = accountForm.getPassword(); // raw value
        String encodedPassword = account.getPassword(); // encoded value

        return passwordEncoder.matches(rawPassword, encodedPassword);

    }
}
