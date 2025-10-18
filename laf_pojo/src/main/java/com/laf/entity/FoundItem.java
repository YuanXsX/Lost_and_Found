package com.laf.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 失物招领实体类
 */
@Data
public class FoundItem implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String itemType;
    private String foundLocation;
    private LocalDateTime foundTime;
    private String imageUrl;
    private Long publisherId;
    private LocalDateTime publishTime;
    private Boolean isClaimed;
}
