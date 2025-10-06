package com.laf.dto;

import lombok.Data;

/**
 * 认领请求数据传输对象
 */
@Data
public class ClaimRequestDTO {
    private Long itemId;
    private Long claimantId;
    private String message;
}