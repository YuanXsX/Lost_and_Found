package com.laf.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 纠纷实体类
 */
@Data
public class Dispute {
    private Long id;
    private Long claimId;
    private Long userId;
    private String reason;
    private String status; // 处理中、已解决、已驳回等
    private LocalDateTime createTime;
    private LocalDateTime resolveTime;
    private String result;
}

