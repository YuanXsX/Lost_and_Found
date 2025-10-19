package com.laf.service.impl;

import com.laf.entity.FoundItem;
import com.laf.mapper.FoundItemMapper;
import com.laf.service.FoundItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class FoundItemServiceImpl implements FoundItemService {

    @Autowired
    private FoundItemMapper foundItemMapper;

    @Override
    public Long createFoundItem(FoundItem foundItem) {
        // 设置默认值
        foundItem.setStatus(0); // 0表示待认领状态
        foundItem.setCreateTime(LocalDateTime.now());
        foundItem.setUpdateTime(LocalDateTime.now());

        // 插入数据库
        int result = foundItemMapper.insert(foundItem);

        // 检查插入结果并返回ID
        if (result > 0) {
            return foundItem.getId(); // 返回自动生成的ID
        } else {
            throw new RuntimeException("创建招领信息失败");
        }
    }
}