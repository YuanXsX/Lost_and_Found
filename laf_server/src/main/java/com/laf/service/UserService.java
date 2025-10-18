package com.laf.service;

import com.laf.dto.UserDTO;
import com.laf.dto.UserLoginDTO;
import com.laf.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    /**
    *用户登录
    * @param userLoginDTO 
    * @return
     * */
    User login(UserLoginDTO userLoginDTO);

    /**
    *用户注册
    * @param userDTO
     * @return
     * */
    void register(UserDTO userDTO);

    /**
     * 绑定校园卡
     * @param userid
     * @param cardNumber
     */
    void bindCard(Long userid, String cardNumber,String realName);


    /**
     * 用id查询用户信息
     * @param userid
     * @return
     */
    User getById(Long userid);
}
