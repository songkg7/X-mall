package com.xmall.xmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    // Login
    // ----
    private String email;

    private String nickname;

    private String password;
    // ----

    private String Location;

}
