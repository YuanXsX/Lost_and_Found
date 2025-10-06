package com.laf.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 失物数据传输对象
 */
@Data
public class LostItemDTO {
    private Long id;
    private String name;
    private String description;
    private String lostLocation;
    private LocalDateTime lostTime;
    private String imageUrl;
    private Long publisherId;
}