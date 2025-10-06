package com.laf.dto;

import lombok.Data;

@Data
public class AdminUpdateDTO {
    private Long id;
    private String email;
    private String phone;
    private String password;
}
