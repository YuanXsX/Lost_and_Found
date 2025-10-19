package com.laf.service;

import com.laf.entity.LostItem;

public interface LostItemService {
    /**
     * 创建失物信息
     * @param lostItem 失物信息对象
     * @return 创建成功的失物ID
     */
    Long createLostItem(LostItem lostItem);
}