package com.laf.mapper;

import com.github.pagehelper.Page;
import com.laf.entity.User;
import com.laf.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    /**
     * 通过一组ID获取用户视图对象
     * @param toIds
     * @return
     */
    Page<UserVO> selectUserVOByIds(List<Long> toIds);
}
