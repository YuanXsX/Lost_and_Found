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
    @Insert("INSERT INTO user (username, password, phone, email, status, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{phone}, #{email}, #{status}, #{createTime}, #{updateTime})")
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
}
