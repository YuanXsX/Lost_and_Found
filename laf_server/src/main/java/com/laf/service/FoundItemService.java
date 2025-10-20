package com.laf.service;

import com.laf.dto.FoundItemDTO;
import com.laf.entity.FoundItem;
import java.util.List;

public interface FoundItemService {
    /**
     * 创建招领信息
     * @param foundItemDTO 招领信息对象
     * @return 创建成功的招领信息ID
     */
    Long createFoundItem(FoundItemDTO foundItemDTO);

    // 新增：删除招领信息
    boolean deleteFoundItem(Long id);

    boolean updateFoundItem(FoundItem foundItem);

    // 新增：根据ID查询单个招领信息
    FoundItem getFoundItemById(Long id);

    // 新增：查询所有招领信息
    List<FoundItem> getAllFoundItems();

    // 新增：根据物品名称模糊查询
    List<FoundItem> getFoundItemsByName(String itemName);

    // 新增：根据地点查询
    List<FoundItem> getFoundItemsByLocation(String foundLocation);

}