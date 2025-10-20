package com.laf.service;

import com.laf.dto.FoundItemDTO;
import com.laf.entity.FoundItem;

public interface FoundItemService {
    /**
     * 创建招领信息
     * @param foundItemDTO 招领信息对象
     * @return 创建成功的招领信息ID
     */
    Long createFoundItem(FoundItemDTO foundItemDTO);

    // 新增：删除招领信息
    boolean deleteFoundItem(Long id);


}