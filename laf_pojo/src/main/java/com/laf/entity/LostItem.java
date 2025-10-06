package com.laf.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 失物招领实体类
 */
@Data
public class LostItem {
    private Long id;
    private String title;
    private String description;
    private String itemType;
    private String lostLocation;
    private LocalDateTime lostTime;
    private String imageUrl;
    private Long publisherId;
    private LocalDateTime publishTime;
    private Boolean isClaimed;
}
