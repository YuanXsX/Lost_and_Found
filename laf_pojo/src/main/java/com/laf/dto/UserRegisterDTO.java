package com.laf.dto;

import lombok.Data;

/**
 * 用户注册数据传输对象
 */
@Data
public class UserRegisterDTO {
    private String campusCardNumber;
    private String username;
    private String password;
    private String email;
    private String phone;
}
