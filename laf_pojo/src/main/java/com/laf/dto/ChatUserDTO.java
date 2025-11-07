package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatUserDTO implements Serializable {
    private Long id;
    private int page;
    private int pageSize;
}
