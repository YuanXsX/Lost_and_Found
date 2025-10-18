package com.laf.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 招领数据传输对象
 */
@Data
public class FoundItemDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String foundLocation;
    private LocalDateTime foundTime;
    private String imageUrl;
    private Long publisherId;
    private  String item_type;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
