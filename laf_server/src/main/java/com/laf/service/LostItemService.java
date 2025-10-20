package com.laf.service;

import com.laf.dto.LostItemDTO;


public interface LostItemService {
    /**
     * 创建失物信息
     * @param lostItemDTO 失物信息对象
     * @return 创建成功的失物ID
     */
    Long createLostItem(LostItemDTO lostItemDTO);
    // 新增：删除失物信息
    boolean deleteLostItem(Long id);
}