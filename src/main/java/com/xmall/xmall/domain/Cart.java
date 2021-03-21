package com.xmall.xmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue
    @Column(name="cart_id")
    private Long id;

    @OneToOne(mappedBy = "cart")
    private Account account;

    @OneToMany
    private List<Item> items = new ArrayList<>();

}
