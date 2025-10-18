package com.laf.mapper;

import com.laf.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    User getByUsername(String username);

    /**
     * 插入用户信息
     * @param user
     */
    @Insert( "INSERT INTO user (card_number, username, password, email, phone, create_time, update_time, is_active, avatar_url) " +
            "VALUES (#{cardNumber}, #{username}, #{password}, #{email}, #{phone}, #{createTime}, #{updateTime}, #{isActive}, #{avatarUrl})")
    void insert(User user);

    /**
     * 通过ID获取用户信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getById(Long id);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 更新用户头像URL
     * @param userId
     * @param publicUrl
     */
    @Select("UPDATE user SET avatar_url = #{publicUrl} WHERE id = #{userId}")
    void updateAvatarUrl(Long userId, String publicUrl);
}
