package com.laf.mapper;
import com.github.pagehelper.Page;
import com.laf.dto.ChatUserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ChatUserMapper {
    @Insert("INSERT INTO chat_user (fromId,toId) " +
            "VALUES (#{fromId}, #{toId})")
    void insertChatUser(Long fromId, Long toId);

   Page<Long> pageQuery(ChatUserDTO chatUserDTO);

}
