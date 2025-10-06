package com.laf.dto;

import lombok.Data;

/**
 * 物品查询数据传输对象
 */
@Data
public class ItemQueryDTO {
    private String type; // lost or found
    private String name;
    private String location;
    private String timeRange; // 可自定义格式
}