package com.laf.service;

import com.laf.dto.ItemQueryDTO;
import com.laf.dto.UserDTO;
import com.laf.dto.UserLoginDTO;
import com.laf.entity.User;
import com.laf.result.PageResult;
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

    /**
     *查询用户发布的失物招领信息
     * @param itemQueryDTO
     * @return
     */
    PageResult pageQueryLostOrFoundItems(ItemQueryDTO itemQueryDTO);

    /**
     * 修改用户信息
     */
    void updateUserInfo(User user);
}
