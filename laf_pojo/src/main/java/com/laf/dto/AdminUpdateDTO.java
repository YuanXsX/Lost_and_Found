package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUpdateDTO implements Serializable {
    private Long id;
    private String email;
    private String phone;
    private String password;
}
