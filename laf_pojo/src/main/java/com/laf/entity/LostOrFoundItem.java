package com.laf.entity;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class LostOrFoundItem {
    private Long id;
    private String itemName;        // 物品名称
    private String description;     // 物品描述
    private String Location;   // 丢失或获得地点
    private String Time; // 丢失或获得时间
    private Long publisherId;
    private Integer status;         // 状态
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
    private String imageUrl;        // 物品图片URL
    private String itemType;
    private int lostOrFound;          // 标识是失物还是招领
}
