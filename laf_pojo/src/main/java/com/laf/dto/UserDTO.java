package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户数据传输对象
 */
@Data
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String avatarUrl;
}
