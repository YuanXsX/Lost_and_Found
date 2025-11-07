package com.laf.mapper;

import com.laf.entity.Card;
import com.laf.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CardMapper {
    /**
     * 根据卡号查询用户信息
     * @param cardNumber 卡号
     * @return 用户信息
     */
    @Select("SELECT * FROM card WHERE card_number = #{cardNumber}")
    Card getByCardNumber(String cardNumber);
}
