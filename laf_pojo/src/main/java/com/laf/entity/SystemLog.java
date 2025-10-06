package com.laf.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统日志实体类
 */
@Data
public class SystemLog {
    private Long id;
    private String action;
    private String operator;
    private LocalDateTime operateTime;
    private String detail;
}
