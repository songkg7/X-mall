package com.xmall.xmall;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 4, max = 20)
    private String password;

}
