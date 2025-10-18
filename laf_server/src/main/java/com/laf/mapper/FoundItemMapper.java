package com.laf.mapper;

import com.laf.entity.FoundItem;
import org.apache.ibatis.annotations.Insert;
import org.mapstruct.Mapper;

@Mapper
public interface FoundItemMapper {
    /**
     * 插入招领信息
     */
    @Insert("insert into found_item (item_name, description, found_location, found_time, contact_info, status, create_time, update_time) " +
            "values (#{itemName}, #{description}, #{foundLocation}, #{foundTime}, #{contactInfo}, #{status}, #{createTime}, #{updateTime})")
    void insert(FoundItem foundItem);
}
