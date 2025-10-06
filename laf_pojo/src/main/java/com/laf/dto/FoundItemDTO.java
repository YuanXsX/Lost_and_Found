package com.laf.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 招领数据传输对象
 */
@Data
public class FoundItemDTO {
    private Long id;
    private String name;
    private String description;
    private String foundLocation;
    private LocalDateTime foundTime;
    private String imageUrl;
    private Long publisherId;
}
