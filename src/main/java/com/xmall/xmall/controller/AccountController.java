package com.xmall.xmall.controller;

import com.xmall.xmall.AccountForm;
import com.xmall.xmall.SignUpForm;
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

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final SignUpFormValidator signUpFormValidator;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute(new SignUpForm());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    // 여러 값을 받아올 때는 @ModelAttribute 가 필요하지만 생략이 가능하다.
    public String signUpProcess(@Valid SignUpForm signUpForm,  Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute(signUpForm);
            return "/sign-up";
        }

//        signUpFormValidator.validate(signUpForm, errors);
//        if (errors.hasErrors()) {
//            model.addAttribute(signUpForm);
//            return "/sign-up";
//        }

        accountService.signUp(signUpForm);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new AccountForm());
        return "login";
    }

    // FIXME: refactoring
    @PostMapping("/login")
    public String loginProcess(@Valid AccountForm accountForm, Model model, Errors errors) {

        Account findAccount = accountRepository.findByEmail(accountForm.getEmail());

        if (findAccount == null) {
            return "login";
        }

        boolean loginConfirm = accountService.login(accountForm, findAccount);

        if (!loginConfirm) {
            return "login";
        }

        return "redirect:/";

    }
}
