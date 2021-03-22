package com.xmall.xmall.service;

import com.xmall.xmall.account.UserAccount;
import com.xmall.xmall.config.AppProperties;
import com.xmall.xmall.form.CheckPwdForm;
import com.xmall.xmall.form.SignUpForm;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.mail.EmailMessage;
import com.xmall.xmall.mail.EmailService;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final AppProperties appProperties;
    private final TemplateEngine templateEngine;
    private final EmailService emailService;

    // NOTE: 초기화
    // Account 가 한명도 없다면, 아래 코드가 실행된다.
    @PostConstruct
    public void init() {

        if (accountRepository.count() == 0) {
            Account account = Account.builder()
                    .email("admin@example.com")
                    .nickname("admin")
                    .password(passwordEncoder.encode("12345678"))
                    .name("관리자")
                    .phone("01011111111")
                    .build();

            accountRepository.save(account);
        }

    }

    // 회원가입
    public Account signUp(SignUpForm signUpForm) {

        // lombok builder 를 사용해서 값을 설정해주기
        // - 아래 주석처리된 코드와 똑같이 동작한다.
        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .name(signUpForm.getName())
                .phone(signUpForm.getPhone())
                .build();

        account.generateEmailCheckToken();

        // 회원 저장 후 이메일을 보내주기 위해 저장된 회원의 정보를 가져온다
        Account newAccount = accountRepository.save(account);
        sendSignUpConfirmEmail(newAccount);
        return newAccount;

    }

    // Security Login
    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public void emailVerifiedConfirm(Account account) {
        account.completeSignUp();
        login(account);
    }

    public void sendSignUpConfirmEmail(Account account) {
        Context context = new Context();
        context.setVariable("link", "/check-email-token?token=" +
                account.getEmailCheckToken() +
                "&email=" + account.getEmail());
        context.setVariable("nickname", account.getNickname());
        context.setVariable("linkName", "이메일 인증하기");
        context.setVariable("message", "X-mall 온라인매장을 이용하려면 링크를 클릭하세요");
        context.setVariable("host", appProperties.getHost());

        String message = templateEngine.process("mail/simple-link", context);

        EmailMessage emailMessage = EmailMessage.builder()
                .to(account.getEmail())
                .subject("X-mall, 회원가입 인증")
                .message(message)
                .build();

        emailService.sendEmail(emailMessage);
    }

    //    회원 탈퇴
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    //    비밀번호 변경
    public void changePwd(Account account, CheckPwdForm checkPwdForm) {
        String encodePwd = passwordEncoder.encode(checkPwdForm.getNew_pwd_check());
        account.setPassword(encodePwd);
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrNickname) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(emailOrNickname);
        String authority = "ROLE_USER";

        if (account == null) {
            account = accountRepository.findByNickname(emailOrNickname);
        }

        if (account == null) {
            throw new UsernameNotFoundException(emailOrNickname);
        }

        if (account.getEmail().equals("admin@example.com")) {
            authority = "ROLE_ADMIN";
        }

        return new UserAccount(account, authority);

    }

    public void sendLoginLink(Account account) {
        Context context = new Context();
        context.setVariable("link", "/check-email-token?token=" +
                account.getEmailCheckToken() +
                "&email=" + account.getEmail());
        context.setVariable("nickname", account.getNickname());
        context.setVariable("linkName", "X-mall Login");
        context.setVariable("message", "로그인하려면 아래 링크를 클릭하세요");
        context.setVariable("host", appProperties.getHost());

        String message = templateEngine.process("mail/simple-link", context);

        EmailMessage emailMessage = EmailMessage.builder()
                .to(account.getEmail())
                .subject("X-mall, Login link")
                .message(message)
                .build();

        emailService.sendEmail(emailMessage);
    }
}
