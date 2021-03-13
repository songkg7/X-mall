package com.xmall.xmall.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    // Login
    // ----
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;
    // ----

    private String Location;

    // Email
    // - token
    private String emailCheckToken;
    private boolean emailVerified;


    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailVerified = false;
    }
}
