package com.laf.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoundItemVO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String foundTime;
    private String foundPlace;
    private String itemType;
    private String imageUrl;
    private UserVO publisher;
    private String status;
    private BigDecimal price;
}

