package com.laf.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 认领实体类
 */
@Data
public class Claim {
    private Long id;
    private Long lostItemId;
    private Long foundItemId;
    private Long claimerId;
    private Long confirmerId;
    private String status; // 申请中、审核中、已认领、已拒绝等
    private LocalDateTime applyTime;
    private LocalDateTime confirmTime;
    private String remark;
}

