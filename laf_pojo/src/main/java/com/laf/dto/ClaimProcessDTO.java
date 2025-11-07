package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 理赔处理数据传输对象
 */
@Data
public class ClaimProcessDTO implements Serializable {
    private Long claimId;
    private String status; // pending, approved, rejected
}