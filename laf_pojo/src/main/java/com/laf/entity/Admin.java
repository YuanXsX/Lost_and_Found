package com.laf.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 管理员实体类
 */
@Data
public class Admin {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isActive;
}

