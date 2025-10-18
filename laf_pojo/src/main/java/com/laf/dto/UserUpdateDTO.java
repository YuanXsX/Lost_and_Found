package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新数据传输对象
 */
@Data
public class UserUpdateDTO implements Serializable {
    private String username;
    private String email;
    private String phone;
    private String password;
    private Boolean isActive;
}
