package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 认领请求数据传输对象
 */
@Data
public class ClaimRequestDTO implements Serializable {
    private Long itemId;
    private Long claimantId;
    private String message;
}