package com.xmall.xmall.controller;

import com.xmall.xmall.account.CurrentAccount;
import com.xmall.xmall.domain.Account;
import com.xmall.xmall.form.CheckPwdForm;
import com.xmall.xmall.form.SignUpForm;
import com.xmall.xmall.repository.AccountRepository;
import com.xmall.xmall.service.AccountService;
import com.xmall.xmall.validator.SignUpFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final SignUpFormValidator signUpFormValidator;
    private final PasswordEncoder passwordEncoder;

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

    @PostMapping("/sign-up")
    public String signUpProcess(@Valid SignUpForm signUpForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute(signUpForm);
            return "account/sign-up";
        }
        Account account = accountService.signUp(signUpForm);
        accountService.login(account);
        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String email, String token, Model model){
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            model.addAttribute("error", "wrong.email");
            return "account/checked-email";
        }

        if (!account.isValidToken(token)) {
            model.addAttribute("error", "wrong.email");
            return "account/checked-email";
        }

        accountService.emailVerifiedConfirm(account);
        model.addAttribute("numberOfUser", accountRepository.count());
        model.addAttribute("nickname", account.getNickname());
        return "account/checked-email";
    }

    @GetMapping("/check-email")
    public String checkEmail(@CurrentAccount Account account, Model model) {
        model.addAttribute("email", account.getEmail());
        return "account/check-email";
    }

    @GetMapping("/resend-confirm-email")
    public String resendConfirmEmail(@CurrentAccount Account account, Model model) {
        if (!account.canSendConfirmEmail()) {
            model.addAttribute("error", "인증 이메일은 1시간에 한번만 전송할 수 있습니다.");
            model.addAttribute("email", account.getEmail());
            return "account/check-email";
        }

        accountService.sendSignUpConfirmEmail(account);
        return "redirect:/";
    }

    // Password 없이 로그인 기능
    @GetMapping("email-login")
    public String emailLoginForm() {
        return "account/email-login";
    }

    @PostMapping("email-login")
    public String sendEmailLoginLink(String email, Model model, RedirectAttributes attributes) {
        Account findAccount = accountRepository.findByEmail(email);

        if (findAccount == null) {
            model.addAttribute("error", "유효한 이메일 주소가 아닙니다.");
            return "account/email-login";
        }

        if (!findAccount.canSendConfirmEmail()) {
            model.addAttribute("error", "이메일은 1시간에 한 번만 보낼 수 있습니다.");
            return "account/email-login";
        }

        accountService.sendLoginLink(findAccount);
        attributes.addFlashAttribute("message", "이메일 인증 메일을 발송했습니다.");
        return "redirect:/email-login";
    }

//    비밀번호 변경
    @PostMapping("/pwd_change")
    public String pwd_changeEdit(@CurrentAccount Account account, Model model, CheckPwdForm checkPwdForm, RedirectAttributes redirectAttributes) {
        model.addAttribute(account);

        boolean resultPwdCheck = passwordEncoder.matches(checkPwdForm.getCurrent_pwd(), account.getPassword());
        if (!resultPwdCheck) {
            model.addAttribute("error1", "비밀번호가 일치하지 않습니다.");
            return "myPage/pwd_change";
        }

        boolean resultPwdCheck2 = passwordEncoder.matches(checkPwdForm.getNew_pwd_check(), account.getPassword());
        if (resultPwdCheck2) {
            model.addAttribute("error2", "변경하려는 비밀번호가 기존 비밀번호와 일치합니다.");
            return "myPage/pwd_change";
        }

        accountService.changePwd(account, checkPwdForm);

        redirectAttributes.addFlashAttribute("message", "비밀번호가 성공적으로 변경 되었습니다.");
        return "redirect:/myPage/pwd_change";
    }

    // 회원 탈퇴
    @GetMapping("/myPage/withdrawal")
    public String withdrawal(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new CheckPwdForm());
        return "myPage/withdrawal";
    }

    // 회원 탈퇴
    @PostMapping("/withdrawal")
    public String withdrawalDelete(@CurrentAccount Account account, Model model,
                                   CheckPwdForm checkPwdForm) {

        model.addAttribute(account);
        boolean resultPwdCheck = passwordEncoder.matches(checkPwdForm.getCurrent_pwd(), account.getPassword());

        if (!resultPwdCheck) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "myPage/withdrawal";
        }

        if (!accountService.orderIsEmpty(account)) {
            model.addAttribute("error", "주문을 먼저 취소한 뒤 회원탈퇴를 시도해주세요.");
            return "myPage/withdrawal";
        }

        accountService.delete(account);
        return "redirect:/logout";
    }
}
