package com.laf.service;

import com.laf.dto.ChatUserDTO;
import com.laf.dto.ClaimProcessDTO;
import com.laf.dto.ClaimRequestDTO;
import com.laf.entity.Claim;
import com.laf.result.PageResult;

public interface ClaimService {
    /**
     * 创建认领请求
     */
    void createClaimRequest(ClaimRequestDTO claimDTO);

    /**
     * 处理认领请求
     */
    void handleClaimRequest(ClaimProcessDTO claimProcessDTO);

    /**
     * 查询认领请求
     */
    String getClaimRequest(Long requestId);

    /**
     * 存储对话用户数据库
     *
     */
    void uploadchatUser(Long fromId, Long toId);


    /**
     * 查询对话用户数据库
     *
     */
    PageResult seekChatuser(ChatUserDTO chatUserDTO);

}
