package com.laf.service.impl;

import com.laf.dto.LostItemDTO;
import com.laf.entity.LostItem;
import com.laf.mapper.LostItemMapper;
import com.laf.service.LostItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class LostItemServiceImpl implements LostItemService {

    @Autowired
    private LostItemMapper lostItemMapper;

    @Override
    public Long createLostItem(LostItemDTO lostItemDTO) {
        // 设置默认值
        LostItem lostItem = new LostItem();
        BeanUtils.copyProperties(lostItemDTO, lostItem);
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

    @Override
    public boolean deleteLostItem(Long id) {
        log.info("删除失物信息，ID: {}", id);
        int result = lostItemMapper.deleteById(id);
        return result > 0;

    }

    @Override
    public boolean updateLostItem(LostItem lostItem) {
        log.info("更新失物信息，ID: {}", lostItem.getId());

        // 设置更新时间
        lostItem.setUpdateTime(LocalDateTime.now());

        int result = lostItemMapper.update(lostItem);
        return result > 0;
    }
}