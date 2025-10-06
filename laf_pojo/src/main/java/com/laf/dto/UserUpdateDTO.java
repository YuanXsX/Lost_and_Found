package com.laf.dto;

import lombok.Data;

/**
 * 用户更新数据传输对象
 */
@Data
public class UserUpdateDTO {
    private String username;
    private String email;
    private String phone;
    private String password;
    private Boolean isActive;
}
