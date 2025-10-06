package com.laf.dto;

import lombok.Data;

/**
 * 理赔处理数据传输对象
 */
@Data
public class ClaimProcessDTO {
    private Long claimId;
    private String status; // pending, approved, rejected
    private String handlerMessage;
}