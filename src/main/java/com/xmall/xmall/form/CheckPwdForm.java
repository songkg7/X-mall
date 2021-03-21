package com.xmall.xmall.form;

import lombok.Data;

@Data
public class CheckPwdForm {

    private String current_pwd;

    private String new_pwd_check;
}
