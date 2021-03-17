package com.xmall.xmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int item_id;

    private String Location;

    private String state;

    private int total_price;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
}
