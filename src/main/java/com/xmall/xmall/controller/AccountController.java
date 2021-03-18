package com.xmall.xmall.controller;

import com.xmall.xmall.form.SignUpForm;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.AccountRepository;
import com.xmall.xmall.service.AccountService;
import com.xmall.xmall.validator.SignUpFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final SignUpFormValidator signUpFormValidator;

    /**
     * Custom validator
     */
    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute(new SignUpForm());

        return "account/sign-up";
    }

    // TODO: addFlashAttribute
    @PostMapping("/sign-up")
    // 여러 값을 받아올 때는 @ModelAttribute 가 필요하지만 생략이 가능하다.
    public String signUpProcess(@Valid SignUpForm signUpForm,  Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 로그인 실패시 화면에 에러 정보를 같이 담아서 보내주기
            model.addAttribute(signUpForm);
            return "account/sign-up";
        }
//        signUpFormValidator.validate(signUpForm, errors);
        if (errors.hasErrors()) {
            model.addAttribute(signUpForm);
            return "account/sign-up";
        }
        // 회원가입한 유저 정보 가져오기
        Account account = accountService.signUp(signUpForm);
        // 회원가입 후 바로 로그인 처리
        accountService.login(account);

        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String email, String token, Model model){
//        /check-email-token?token=7647e3be-acd9-4786-a908-6bfd04beb87f&email=songkg7@gmail.com
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            model.addAttribute("error", "wrong.email");

            return "account/checkedEmail";
        }

        if (!account.getEmailCheckToken().equals(token)) {
            model.addAttribute("error", "wrong.email");

            return "account/checkedEmail";
        }
        // 위를 모두 통과하면 정식 회원가입 절차 완료
        // 인증 뷰로 데이터 보여주기
        account.setEmailVerified(true);
        account.setJoinedAt(LocalDateTime.now());
        model.addAttribute("numberOfUser", accountRepository.count());
        model.addAttribute("nickname", account.getNickname());

        return "account/checkedEmail";

    }
}
