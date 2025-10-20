package com.laf.mapper;

import com.laf.entity.LostItem;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface LostItemMapper {

    /**
     * 插入失物信息
     */
    @Insert("INSERT INTO lost_item (item_name, description, lost_location, lost_time, publisher_id, status, create_time, update_time, image_url, item_type)" +
            "VALUES (#{itemName}, #{description}, #{lostLocation}, #{lostTime}, #{publisherId}, #{status}, #{createTime}, #{updateTime}, #{imageUrl}, #{itemType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(LostItem lostItem);


    // 新增：根据ID删除失物信息
    @Delete("DELETE FROM lost_item WHERE id = #{id}")
    int deleteById(Long id);



    // 新增：根据ID更新失物信息
    @Update("UPDATE lost_item SET " +
            "item_name = #{itemName}, " +
            "description = #{description}, " +
            "lost_location = #{LostLocation}, " +      // 使用 #{LostLocation}
            "lost_time = #{LostTime}, " +              // 使用 #{LostTime}
            "publisher_id = #{publisherId}, " +
            "status = #{status}, " +
            "update_time = #{updateTime}, " +
            "image_url = #{imageUrl}, " +
            "item_type = #{itemType} " +
            "WHERE id = #{id}")
    int update(LostItem lostItem);

    // 新增：根据ID查询单个失物信息
    @Select("SELECT * FROM lost_item WHERE id = #{id}")
    LostItem selectById(Long id);

    // 新增：查询所有失物信息
    @Select("SELECT * FROM lost_item ORDER BY create_time DESC")
    List<LostItem> selectAll();

    // 新增：根据物品名称模糊查询
    @Select("SELECT * FROM lost_item WHERE item_name LIKE CONCAT('%', #{itemName}, '%') ORDER BY create_time DESC")
    List<LostItem> selectByItemName(String itemName);

    // 新增：根据地点查询
    @Select("SELECT * FROM lost_item WHERE lost_location LIKE CONCAT('%', #{lostLocation}, '%') ORDER BY create_time DESC")
    List<LostItem> selectByLocation(String lostLocation);

}