package com.xmall.xmall.service;

import com.xmall.xmall.account.UserAccount;
import com.xmall.xmall.form.AccountForm;
import com.xmall.xmall.form.SignUpForm;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    // 회원가입
    public void signUp(SignUpForm signUpForm) {

        // lombok builder 를 사용해서 값을 설정해주기
        // - 아래 주석처리된 코드와 똑같이 동작한다.
        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();

        // getter, setter 를 사용하는 전통적인 방법
//        Account account = new Account();
//        account.setEmail(signUpForm.getEmail());
//        account.setNickname(signUpForm.getNickname());
//        String encodePwd = passwordEncoder.encode(signUpForm.getPassword());
//        account.setPassword(encodePwd);

        // 회원 저장 후 이메일을 보내주기 위해 저장된 회원의 정보를 가져온다
        Account newAccount = accountRepository.save(account);

        // TODO: email 보내기
        // - 가짜 객체(ConsoleMailSender)를 만들어서 토큰을 콘솔에 출력
        // - 아직 메일서버와 연결되지 않았기 때문에 추후 진짜 메일로 바꾼다.

        // 메일 관련 기능을 이용하기 위한 SimpleMailMessage 생성
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // Random token 값을 생성해주는 메소드를 작성해주기
        newAccount.generateEmailCheckToken();

        // 이메일 내용 작성
        mailMessage.setSubject("X-mall 회원가입 인증"); // Email 의 제목
        mailMessage.setTo(newAccount.getEmail()); // 보낼 메일 주소
        mailMessage.setText("/check-email-token?token=" + newAccount.getEmailCheckToken()
                + "&email=" + newAccount.getEmail()); // 본문

        // 메일 보내기
        javaMailSender.send(mailMessage);

    }

    // 로그인
    // TODO: Security 처리하기
    // - 아직은 겉으로만 로그인일 뿐 실제 로그인 처리가 아니다.
    public boolean login(AccountForm accountForm, Account account) {

        String rawPassword = accountForm.getPassword(); // raw value
        String encodedPassword = account.getPassword(); // encoded value

        return passwordEncoder.matches(rawPassword, encodedPassword);

    }

    // Security Login
    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrNickname) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(emailOrNickname);
        if (account == null) {
            account = accountRepository.findByNickname(emailOrNickname);
        }

        if (account == null) {
            throw new UsernameNotFoundException(emailOrNickname);
        }

        return new UserAccount(account);

    }
}
