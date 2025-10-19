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
    @Insert("INSERT INTO lost_item (item_name, description, lost_location, lost_time, publisher_id, status, create_time, update_time, image_url, item_type)" +
            "VALUES (#{itemName}, #{description}, #{lostLocation}, #{lostTime}, #{publisherId}, #{status}, #{createTime}, #{updateTime}, #{imageUrl}, #{itemType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(LostItem lostItem);

}