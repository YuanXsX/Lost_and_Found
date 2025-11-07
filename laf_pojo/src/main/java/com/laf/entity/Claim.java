package com.laf.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 认领实体类
 */
@Data
public class Claim implements Serializable {
    private Long lostOrFoundItemId;
    private Long toId;
    private Long claimantId;
    private String claimStatus; // 申请中、审核中、已认领、已拒绝等
    private LocalDateTime applyTime;
    private LocalDateTime confirmTime;
}

