package com.laf.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 失物数据传输对象
 */
@Data
public class LostItemDTO implements Serializable {
    private Long id;
    private String itemName;
    private String description;
    private String lostLocation;
    private LocalDateTime lostTime;
    private String imageUrl;
    private Long publisherId;
    private String itemType;
}