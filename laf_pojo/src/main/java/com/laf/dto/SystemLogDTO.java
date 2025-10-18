package com.laf.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志数据传输对象
 */
@Data
public class SystemLogDTO implements Serializable {
    private Long id;
    private String action;
    private String operator;
    private LocalDateTime operateTime;
    private String detail;
}

