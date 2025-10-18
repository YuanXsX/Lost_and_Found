package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册数据传输对象
 */
@Data
public class UserRegisterDTO implements Serializable {
    private String cardNumber;
    private String username;
    private String password;
    private String email;
    private String phone;
}
