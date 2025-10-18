package com.laf.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
public class User implements Serializable {
    private Long id;
    private String cardNumber;
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isActive;
    private String avatarUrl;
}
