package com.laf.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemLogVO implements Serializable {
    private Long id;
    private String action;
    private String operator;
    private String operateTime;
    private String detail;
}

