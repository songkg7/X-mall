package com.xmall.xmall.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    // Login
    // ----
    @Email
    @NotBlank
    private String email;

    private String nickname;

    @NotBlank
    @Length(min = 8, max = 50)
    private String password;
    // ----

    private String Location;

}
