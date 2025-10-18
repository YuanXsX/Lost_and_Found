package com.laf.service.impl;

import com.laf.mapper.FoundItemMapper;
import com.laf.mapper.UserMapper;
import com.laf.service.FoundItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoundItemServiceImpl implements FoundItemService {
    @Autowired
    private FoundItemMapper foundItemMapper;


    @Override
    public void reportFoundItem() {

        System.out.println("招领物品信息已上报");
    }
}
