package com.laf.service.impl;

import com.laf.dto.FoundItemDTO;
import com.laf.entity.FoundItem;
import com.laf.mapper.FoundItemMapper;
import com.laf.service.FoundItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoundItemServiceImpl implements FoundItemService {

    @Autowired
    private FoundItemMapper foundItemMapper;

    @Override
    public Long createFoundItem(FoundItemDTO foundItemDTO) {
        // 设置默认值
        FoundItem foundItem = new FoundItem();
        BeanUtils.copyProperties(foundItemDTO, foundItem);
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
    @Override
    public boolean deleteFoundItem(Long id) {
        log.info("删除招领信息，ID: {}", id);
        int result = foundItemMapper.deleteById(id);
        return result > 0;
    }
}