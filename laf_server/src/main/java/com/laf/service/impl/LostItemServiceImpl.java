package com.laf.service.impl;

import com.laf.entity.LostItem;
import com.laf.mapper.LostItemMapper;
import com.laf.service.LostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class LostItemServiceImpl implements LostItemService {

    @Autowired
    private LostItemMapper lostItemMapper;

    @Override
    public Long createLostItem(LostItem lostItem) {
        // 设置默认值
        lostItem.setStatus(0); // 0表示丢失状态
        lostItem.setCreateTime(LocalDateTime.now());
        lostItem.setUpdateTime(LocalDateTime.now());

        // 插入数据库
        int result = lostItemMapper.insert(lostItem);

        // 检查插入结果并返回ID
        if (result > 0) {
            return lostItem.getId(); // 返回自动生成的ID
        } else {
            throw new RuntimeException("创建丢失信息失败");
        }
    }
}