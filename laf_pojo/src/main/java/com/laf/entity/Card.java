package com.laf.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Card implements Serializable {
    private Long id;
    private String cardNumber;
    private String realName;
}
