package com.laf.mapper;

import com.laf.entity.LostItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface LostItemMapper {

    /**
     * 插入失物信息
     */
    @Insert("INSERT INTO lost_item (item_name, description, lost_location, lost_time, contact_info, status, create_time, update_time, image_url) " +
            "VALUES (#{itemName}, #{description}, #{lostLocation}, #{lostTime}, #{contactInfo}, #{status}, #{createTime}, #{updateTime}, #{imageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(LostItem lostItem);

}