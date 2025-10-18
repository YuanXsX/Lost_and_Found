package com.laf.service.impl;

import com.laf.constant.MessageConstant;
import com.laf.dto.UserLoginDTO;
import com.laf.entity.User;
import com.laf.exception.AccountLockedException;
import com.laf.exception.AccountNotFoundException;
import com.laf.exception.PasswordErrorException;
import com.laf.mapper.UserMapper;
import com.laf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     *用户登录
     * @param userLoginDTO
     * @return
     * */
    public User login(UserLoginDTO userLoginDTO) {
        String username=userLoginDTO.getUsername();
        String password=userLoginDTO.getPassword();
        //1.根据用户名查询数据库中的数据
        User user=userMapper.getByUsername(username);
        //2.处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if(user==null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.USER_NOT_FOUND);
        }

        //password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.USERNAME_OR_PASSWORD_ERROR);
        }
        if(!user.getIsActive()) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.FORBIDDEN);
        }
        //3.返回实体对象
        return user;
    }
}
