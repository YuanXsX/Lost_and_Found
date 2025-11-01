package com.laf.controller.user;

/**
 * 用户管理
 */

import com.laf.constant.JwtClaimsConstant;
import com.laf.context.BaseContext;
import com.laf.dto.ItemQueryDTO;
import com.laf.dto.UserDTO;
import com.laf.dto.UserLoginDTO;
import com.laf.entity.User;
import com.laf.properties.JwtProperties;
import com.laf.result.PageResult;
import com.laf.result.Result;
import com.laf.service.UserService;
import com.laf.utils.JwtUtil;
import com.laf.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/laf/user/customer")
@Slf4j
@Api(tags = "用户管理子系统")
public class UserController {
    @Autowired
    private UserService UserService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    public Result<UserLoginVO> login(UserLoginDTO userLoginDTO) {
        log.info("用户登录，用户名：{}", userLoginDTO.getUsername());
        User user = UserService.login(userLoginDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, user.getId());
        // 生成JWT令牌
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        // 返回登录结果
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .cardNumber(user.getCardNumber())
                .token(token)
                .build();
        log.info("用户登录成功，用户id：{}", user.getId());
        return Result.success(userLoginVO);
    }

    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册接口")
    public Result<String> register(UserDTO userDTO) {
        log.info("用户注册，用户名：{}", userDTO.getUsername());
        UserService.register(userDTO);
        return Result.success("注册成功");
    }

    /**
     * 绑定校园卡
     * @param cardNumber
     * @param real_name
     * return
     */
    @PostMapping("/bindCard")
    @ApiOperation(value = "绑定校园卡", notes = "绑定校园卡接口")
    public Result<String> bindCard(String cardNumber,String real_name) {
        Long userid = BaseContext.getCurrentId();
        log.info("绑定校园卡，用户id：{}，卡号：{}", userid, cardNumber);
        UserService.bindCard(userid,cardNumber,real_name);
        return Result.success("绑定成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息接口")
    public Result<User> getUserInfo() {
        Long userid = BaseContext.getCurrentId();
        log.info("获取用户信息，用户id：{}", userid);
        User user = UserService.getById(userid);
        return Result.success(user);
    }



    /**
     *查询用户发布的失物招领信息
     * @param
     * @return
     */
    @GetMapping("/PageQueryUserItems")
    @ApiOperation(value = "查询用户发布的失物招领信息", notes = "查询用户发布的失物招领信息接口")
    public Result<PageResult> pageQueryUserItems(ItemQueryDTO itemQueryDTO) {
        Long userid = BaseContext.getCurrentId();
        log.info("查询用户发布的失物招领信息，用户id：{}", userid);
        log.info("失物还是招领：{}", itemQueryDTO.getType());
        PageResult pageResult = UserService.pageQueryLostOrFoundItems(itemQueryDTO);
        return Result.success(pageResult);
    }
}

