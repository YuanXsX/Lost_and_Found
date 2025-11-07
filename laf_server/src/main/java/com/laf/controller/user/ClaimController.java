package com.laf.controller.user;

import com.laf.context.BaseContext;
import com.laf.dto.ChatUserDTO;
import com.laf.dto.ClaimProcessDTO;
import com.laf.dto.ClaimRequestDTO;
import com.laf.entity.Claim;
import com.laf.result.PageResult;
import com.laf.result.Result;
import com.laf.service.ClaimService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/laf/user/chaim")
@Slf4j
@Api(tags = "失物认领子系统")
public class ClaimController {
    @Autowired
    private ClaimService claimService;

    /**
     * 认领失物申请
     */
    @PostMapping("/create")
    public Result<String> claimItem(ClaimRequestDTO claimRequestDTO) {
        log.info("认领失物申请，失物ID：{}", claimRequestDTO.getItemId());
        claimService.createClaimRequest(claimRequestDTO);
        return Result.success("申请成功");
    }

    /**
     * 处理认领申请
     */
    @PostMapping("/handle")
    public Result<String> handleClaimRequest(ClaimProcessDTO claimProcessDTO) {
        log.info("处理认领申请，申请ID：{}，是否批准：{}", claimProcessDTO.getClaimId(), claimProcessDTO.getStatus());
        claimService.handleClaimRequest(claimProcessDTO);
        return Result.success("处理成功");
    }

    @GetMapping("/{id}")
    public Result<String> getClaimRequestStatus(@PathVariable Long id) {
        log.info("查询认领请求状态，申请ID：{}", id);
        String status = claimService.getClaimRequest(id);
        return Result.success(status);
    }

    @PostMapping("/uploadchatUser")
    @ApiOperation(value = "存储对话数据库", notes = "存储对话数据库接口")
    public Result<String> uploadchatUser(@RequestBody Long toId) {
        Long fromId= BaseContext.getCurrentId();
        log.info("存储对话数据库，fromId：{}，toId：{}", fromId,toId);
        claimService.uploadchatUser(fromId,toId);
        return Result.success("存储成功");
    }

    @GetMapping("/seekChatuser" )
    @ApiOperation(value = "查询对话数据库", notes = "查询对话数据库接口")
    public Result<PageResult> seekChatuser(ChatUserDTO chatUserDTO) {
        Long fromId= BaseContext.getCurrentId();
        chatUserDTO.setId(fromId);
        log.info("查询对话数据库，fromId：{}", fromId);
        PageResult pageResult = claimService.seekChatuser(chatUserDTO);
        return Result.success(pageResult);
    }
}
