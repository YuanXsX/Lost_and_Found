package com.laf.mapper;

import com.laf.entity.FoundItem;
import org.apache.ibatis.annotations.*;
import java.util.List;

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

    // 新增：根据ID查询单个招领信息
    @Select("SELECT * FROM found_item WHERE id = #{id}")
    FoundItem selectById(Long id);

    // 新增：查询所有招领信息
    @Select("SELECT * FROM found_item ORDER BY create_time DESC")
    List<FoundItem> selectAll();

    // 新增：根据物品名称模糊查询
    @Select("SELECT * FROM found_item WHERE item_name LIKE CONCAT('%', #{itemName}, '%') ORDER BY create_time DESC")
    List<FoundItem> selectByItemName(String itemName);

    // 新增：根据地点查询
    @Select("SELECT * FROM found_item WHERE found_location LIKE CONCAT('%', #{foundLocation}, '%') ORDER BY create_time DESC")
    List<FoundItem> selectByLocation(String foundLocation);

}