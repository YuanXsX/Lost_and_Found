package com.laf.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimVO {
    private Long id;
    private LostItemVO lostItem;
    private FoundItemVO foundItem;
    private UserVO claimant;
    private String claimStatus;
    private String applyTime;
    private String confirmTime;
}

