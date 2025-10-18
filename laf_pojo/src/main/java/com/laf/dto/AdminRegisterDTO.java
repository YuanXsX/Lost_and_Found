package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRegisterDTO implements Serializable {
    private String username;
    private String password;
    private String email;
    private String phone;
}
