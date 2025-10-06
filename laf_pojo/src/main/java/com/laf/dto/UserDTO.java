package com.laf.dto;

import lombok.Data;

/**
 * 用户数据传输对象
 */
@Data
public class UserDTO {
    private Long id;
    private String campusCardNumber;
    private String username;
    private String email;
    private String phone;
    private String role;
    private String status;
}
