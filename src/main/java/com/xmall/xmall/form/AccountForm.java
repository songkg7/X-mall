package com.xmall.xmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountForm {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
