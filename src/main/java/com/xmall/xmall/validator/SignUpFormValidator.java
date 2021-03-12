package com.xmall.xmall.validator;

import com.xmall.xmall.SignUpForm;
import com.xmall.xmall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpForm signupForm = (SignUpForm) target;
        if (accountRepository.existsByEmail(signupForm.getEmail())) {
            errors.rejectValue("email", "invalid.email",
                    new Object[]{signupForm.getEmail()}, "이미 사용중인 이메일입니다.");
        }

        if (accountRepository.existsByNickname(signupForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname",
                    new Object[]{signupForm.getNickname()}, "이미 사용중인 닉네임입니다.");
        }
    }
}
