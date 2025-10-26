package com.laf.mapper;

import com.github.pagehelper.Page;
import com.laf.dto.ItemQueryDTO;
import com.laf.entity.LostItem;
import com.laf.entity.LostOrFoundItem;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface LostOrFoundItemMapper {

    /**
     * 插入失物招领信息
     */
    @Insert("INSERT INTO lost_or_found_item (item_name, description, location, time, publisher_id, status, create_time, update_time, image_url, item_type,lost_or_found)" +
            "VALUES (#{itemName}, #{description}, #{Location}, #{Time}, #{publisherId}, #{status}, #{createTime}, #{updateTime}, #{imageUrl}, #{itemType}, #{lostOrFound})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(LostOrFoundItem lostOrFoundItem);


    // 新增：根据ID删除失物招领信息
    @Delete("DELETE FROM lost_or_found_item WHERE id = #{id}")
    int deleteAllById(Long id);



    // 新增：根据ID更新失物招领信息
    @Update("UPDATE lost_or_found_item SET " +
            "item_name = #{itemName}, " +
            "description = #{description}, " +
            "location = #{Location}, " +      // 使用 #{Location}
            "lost_time = #{Time}, " +              // 使用 #{Time}
            "publisher_id = #{publisherId}, " +
            "status = #{status}, " +
            "update_time = #{updateTime}, " +
            "image_url = #{imageUrl}, " +
            "item_type = #{itemType} " +
            "WHERE id = #{id}")
    int updateAll(LostOrFoundItem lostOrFoundItem);

    // 新增：根据ID查询单个失物信息
    @Select("SELECT * FROM lost_or_found_item WHERE id = #{id}")
    LostItem selectLostById(Long id);

    // 新增：查询所有失物信息
    @Select("SELECT * FROM lost_or_found_item ORDER BY create_time DESC")
    List<LostItem> selectLost();


    /**
     * 分页查询失物招领信息
     * @param itemQueryDTO
     */
    Page<LostOrFoundItem> pageQueryLostOrFoundItems(ItemQueryDTO itemQueryDTO);
}