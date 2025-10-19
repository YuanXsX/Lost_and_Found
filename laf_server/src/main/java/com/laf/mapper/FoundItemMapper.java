package com.laf.mapper;

import com.laf.entity.FoundItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface FoundItemMapper {

    /**
     * 插入招领信息
     * 使用你的完整 SQL 语句，包含所有字段
     */
    @Insert("INSERT INTO found_item (item_name, description, found_location, found_time, publisher_id, status, create_time, update_time, image_url, item_type) " +
            "VALUES (#{itemName}, #{description}, #{foundLocation}, #{foundTime}, #{publisherId}, #{status}, #{createTime}, #{updateTime}, #{imageUrl}, #{itemType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FoundItem foundItem);
}