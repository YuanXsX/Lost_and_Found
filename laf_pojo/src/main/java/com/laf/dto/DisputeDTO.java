package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 纠纷数据传输对象
 */
@Data
public class DisputeDTO implements Serializable {
    private Long claimId;
    private Long submitterId;
    private String reason;
    private String status; // pending, resolved, rejected
}