package com.laf.service;

import com.laf.entity.FoundItem;

public interface FoundItemService {
    /**
     * 创建招领信息
     * @param foundItem 招领信息对象
     * @return 创建成功的招领信息ID
     */
    Long createFoundItem(FoundItem foundItem);
}