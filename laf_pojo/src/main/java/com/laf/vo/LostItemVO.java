package com.laf.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LostItemVO {
    private Long id;
    private String title;
    private String description;
    private String lostTime;
    private String lostPlace;
    private String itemType;
    private String imageUrl;
    private UserVO publisher;
    private String status;
    private BigDecimal price;
}
