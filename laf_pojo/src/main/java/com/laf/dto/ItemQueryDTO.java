package com.laf.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 物品查询数据传输对象
 */
@Data
public class ItemQueryDTO implements Serializable {
    private String type; // lost or found
    private String itemName;
    private int page;
    private int pageSize;
}