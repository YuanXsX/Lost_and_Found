package com.laf.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志实体类
 */
@Data
public class SystemLog implements Serializable {
    private Long id;
    private String action;
    private String operator;
    private LocalDateTime operateTime;
    private String detail;
}
