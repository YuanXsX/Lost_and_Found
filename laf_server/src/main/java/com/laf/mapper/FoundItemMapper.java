package com.laf.mapper;

import com.laf.entity.FoundItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;  // 添加这个导入

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


    // 新增：根据ID删除招领信息
    @Delete("DELETE FROM found_item WHERE id = #{id}")
    int deleteById(Long id);


    @Update("UPDATE found_item SET " +
            "item_name = #{itemName}, " +
            "description = #{description}, " +
            "found_location = #{foundLocation}, " +
            "found_time = #{foundTime}, " +
            "publisher_id = #{publisherId}, " +
            "status = #{status}, " +
            "update_time = #{updateTime}, " +
            "image_url = #{imageUrl}, " +
            "item_type = #{itemType} " +
            "WHERE id = #{id}")
    int update(FoundItem foundItem);
}