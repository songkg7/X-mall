package com.xmall.xmall.controller;

import com.xmall.xmall.AccountForm;
import com.xmall.xmall.SignUpForm;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.repository.AccountRepository;
import com.xmall.xmall.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute(new SignUpForm());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpProcess(@Valid SignUpForm signUpForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute(signUpForm);
            return "/sign-up";
        }

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
    public String loginProcess(@Valid AccountForm accountForm, Errors errors, Model model) {

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
