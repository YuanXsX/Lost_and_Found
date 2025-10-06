package com.laf.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统日志数据传输对象
 */
@Data
public class SystemLogDTO {
    private Long id;
    private String action;
    private String operator;
    private LocalDateTime operateTime;
    private String detail;
}

