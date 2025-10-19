package com.laf.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 失物招领实体类
 */
@Data
public class LostItem implements Serializable {
    private Long id;
    private String itemName;        // 物品名称
    private String description;     // 物品描述
    private String LostLocation;   // 丢失地点
    private LocalDateTime LostTime; // 丢失时间
    private Long publisherId;
    private Integer status;         // 状态
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
    private String imageUrl;        // 物品图片URL
    private String itemType;
}