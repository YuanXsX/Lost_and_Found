package com.laf.controller.user;

/**
 * 用户管理
 */

import com.laf.constant.JwtClaimsConstant;
import com.laf.dto.UserLoginDTO;
import com.laf.entity.User;
import com.laf.properties.JwtProperties;
import com.laf.result.Result;
import com.laf.service.UserService;
import com.laf.utils.JwtUtil;
import com.laf.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/laf/user")
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
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}
