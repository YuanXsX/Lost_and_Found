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
public class DisputeVO implements Serializable {
    private Long id;
    private ClaimVO claim;
    private String reason;
    private String status;
    private String createTime;
}

