package com.laf.service.impl;

import com.laf.constant.MessageConstant;
import com.laf.constant.PasswordConstant;
import com.laf.dto.UserDTO;
import com.laf.dto.UserLoginDTO;
import com.laf.entity.Card;
import com.laf.entity.User;
import com.laf.exception.AccountLockedException;
import com.laf.exception.AccountNotFoundException;
import com.laf.exception.PasswordErrorException;
import com.laf.mapper.CardMapper;
import com.laf.mapper.UserMapper;
import com.laf.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private CardMapper cardMapper;

    @Value("${laf.avatar.default-url:/images/default_avatar.jpeg}")
    private String defaultAvatarUrl;

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

        password = DigestUtils.md5DigestAsHex(password.getBytes());
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


    /**
     * 用户注册
     * @param userDTO
     */
    public void register(UserDTO userDTO) {
        User user=new User();
        BeanUtils.copyProperties(userDTO, user);
        // 对密码进行MD5加密
        String password = DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes());
        user.setPassword(password);
        user.setIsActive(true);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        if (user.getAvatarUrl() == null || user.getAvatarUrl().isBlank()) {
            user.setAvatarUrl(defaultAvatarUrl);
        }
        userMapper.insert(user);
    }



    /**
     * 绑定校园卡
     * @param userid
     * @param cardNumber
     */
    public void bindCard(Long userid, String cardNumber,String realName) {
        User user = userMapper.getById(userid);
        Card card = cardMapper.getByCardNumber(cardNumber);
        if(card == null) {
            throw new AccountNotFoundException(MessageConstant.CARD_NOT_FOUND);
        }
        if(!realName.equals(card.getRealName())){
            throw new AccountNotFoundException(MessageConstant.CARD_NOT_TRUE);
        }
        user.setCardNumber(cardNumber);
        userMapper.update(user);
    }

    /**
     * 用id查询用户信息
     * @param userId
     * @return
     */
    public User getById(Long userId) {
        return userMapper.getById(userId);
    }
}
