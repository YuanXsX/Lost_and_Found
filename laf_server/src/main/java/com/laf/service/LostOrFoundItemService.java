package com.laf.service;

import com.laf.dto.LostItemDTO;
import com.laf.entity.LostItem;

import java.util.List;

public interface LostOrFoundItemService {
    /**
     * 创建失物信息
     * @param lostItemDTO 失物信息对象
     * @return 创建成功的失物ID
     */
    Long createLostItem(LostItemDTO lostItemDTO);
    // 新增：删除失物信息
    boolean deleteLostItem(Long id);

    boolean updateLostItem(LostItem lostItem);


    // 新增：根据ID查询单个失物信息
    LostItem getLostItemById(Long id);

    // 新增：查询所有失物信息
    List<LostItem> getAllLostItems();

    // 新增：根据物品名称模糊查询
    List<LostItem> getLostItemsByName(String itemName);

    // 新增：根据地点查询
    List<LostItem> getLostItemsByLocation(String lostLocation);
}