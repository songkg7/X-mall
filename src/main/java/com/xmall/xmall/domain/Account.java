package com.xmall.xmall.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
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

    @Column(unique = true, name = "nickname")
    private String nickname;

    private String name;

    private String phone;

    private String password;

    // ----
    @OneToMany(mappedBy = "account")
    private List<Order> orders = new ArrayList<>();

    // 주소
    @OneToMany
    private List<Address> addressList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Email
    // - token

    private String emailCheckToken;

    private boolean emailVerified = false;

    private LocalDateTime emailCheckTokenGeneratedAt;

    @OneToMany(mappedBy = "account")
    private List<MyReview> reviews = new ArrayList<>();

    // 가입한 날짜
//    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime JoinedAt;

    // UUID 를 사용하여 랜덤한 값을 생성한 후 Account 에 담는다.
    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        // 생성시간 정보를 저장해서 연속적으로 메일을 보내지 못하게한다
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }
    public void completeSignUp() {
        this.emailVerified = true;
        this.JoinedAt = LocalDateTime.now();
    }

    // token 값 검증
    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

    // email 전송 시간 비교
    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }

    @Builder
    public Account(String email, String nickname, String name, String phone, String password, Boolean emailVerified) {
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.emailVerified = emailVerified;
    }
}
