package com.laf.service;

import com.github.pagehelper.Page;
import com.laf.dto.ItemQueryDTO;
import com.laf.entity.LostOrFoundItem;
import java.util.List;

public interface LostOrFoundItemService {

    /**
     * 发布失物或招领信息
     */
    int publishItem(LostOrFoundItem item);

    /**
     * 根据ID删除信息
     */
    int deleteItemById(Long id);

    /**
     * 更新信息
     */
    int updateItem(LostOrFoundItem item);

    /**
     * 根据ID查询信息
     */
    LostOrFoundItem getItemById(Long id);

    /**
     * 查询所有信息
     */
    List<LostOrFoundItem> getAllItems();

    /**
     * 分页查询信息
     */
    Page<LostOrFoundItem> pageQueryItems(ItemQueryDTO itemQueryDTO);
}