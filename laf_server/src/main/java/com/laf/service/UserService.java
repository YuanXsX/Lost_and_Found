package com.laf.service;

import com.laf.dto.UserLoginDTO;
import com.laf.entity.User;

public interface UserService {
    /**
    *用户登录
    * @param userLoginDTO 
    * @return
     * */
    User login(UserLoginDTO userLoginDTO);
    
    /**
    *
    * @param
     * @return
     * */
}
