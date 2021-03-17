package com.xmall.xmall.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    private String name;

    private String phone;

    private String password;
    // ----

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

//    private String Location;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


    // Email
    // - token
    private String emailCheckToken;

    @Builder.Default
    private boolean emailVerified = false;

    // 가입한 날짜
    private LocalDateTime JoinedAt;

    // UUID 를 사용하여 랜덤한 값을 생성한 후 Account 에 담는다.
    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }
}
