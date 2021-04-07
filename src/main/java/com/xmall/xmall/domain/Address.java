package com.xmall.xmall.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String detailsAddress;

    private String extraAddress;

    @Column(nullable = false)
    private String postcode;

    public Address(String address, String detailsAddress, String extraAddress, String postcode) {
        this.address = address;
        this.detailsAddress = detailsAddress;
        this.extraAddress = extraAddress;
        this.postcode = postcode;
    }
}
