package com.xmall.xmall.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String main;

    private String details;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
