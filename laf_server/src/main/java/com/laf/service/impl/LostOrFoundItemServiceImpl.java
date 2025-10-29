package com.laf.service.impl;
import com.github.pagehelper.Page;
import com.laf.dto.ItemQueryDTO;
import com.laf.entity.LostOrFoundItem;
import com.laf.mapper.LostOrFoundItemMapper;
import com.laf.service.LostOrFoundItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LostOrFoundItemServiceImpl implements LostOrFoundItemService {

    @Autowired
    private LostOrFoundItemMapper lostOrFoundItemMapper;

    @Override
    public int publishItem(LostOrFoundItem item) {
        // 设置创建时间和更新时间
        item.setCreateTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        // 设置默认状态，例如：0-待认领/待领取，1-已认领/已领取
        if (item.getStatus() == null) {
            item.setStatus(0);
        }
        return lostOrFoundItemMapper.insert(item);
    }

    @Override
    public int deleteItemById(Long id) {
        return lostOrFoundItemMapper.deleteById(id);
    }

    @Override
    public int updateItem(LostOrFoundItem item) {
        // 更新修改时间
        item.setUpdateTime(LocalDateTime.now());
        return lostOrFoundItemMapper.update(item);
    }

    @Override
    public LostOrFoundItem getItemById(Long id) {
        return lostOrFoundItemMapper.selectById(id);
    }

    @Override
    public List<LostOrFoundItem> getAllItems() {
        return lostOrFoundItemMapper.selectAll();
    }

    @Override
    public Page<LostOrFoundItem> pageQueryItems(ItemQueryDTO itemQueryDTO) {
        // 使用 PageHelper 开始分页
        com.github.pagehelper.PageHelper.startPage(itemQueryDTO.getPage(), itemQueryDTO.getPageSize());
        return lostOrFoundItemMapper.pageQuery(itemQueryDTO);
    }
}