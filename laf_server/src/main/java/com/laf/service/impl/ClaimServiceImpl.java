package com.laf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.laf.context.BaseContext;
import com.laf.dto.ChatUserDTO;
import com.laf.dto.ClaimProcessDTO;
import com.laf.dto.ClaimRequestDTO;
import com.laf.entity.Claim;
import com.laf.entity.LostOrFoundItem;
import com.laf.mapper.ChatUserMapper;
import com.laf.mapper.ClaimMapper;
import com.laf.mapper.LostOrFoundItemMapper;
import com.laf.result.PageResult;
import com.laf.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Autowired
    private ClaimMapper claimMapper;
    @Autowired
    private LostOrFoundItemMapper lostOrFoundItemMapper;
    @Autowired
    private ChatUserMapper chatUserMapper;

    @Override
    public void createClaimRequest(ClaimRequestDTO claimDTO) {
        Long userId = BaseContext.getCurrentId();
        Claim claim = new Claim();
        claim.setLostOrFoundItemId(claimDTO.getItemId());
        claim.setClaimantId(userId);
        claim.setToId(claimDTO.getToId());
        claim.setClaimStatus("0"); // 0表示待处理
        claim.setApplyTime(LocalDateTime.now());
        claimMapper.createChaimRecord(claim);
    }

    @Override
    public void handleClaimRequest(ClaimProcessDTO claimProcessDTO) {
        Claim claim = claimMapper.getClaimById(claimProcessDTO.getClaimId());
        if (claim != null && BaseContext.getCurrentId().equals(claim.getToId())) {
            claim.setClaimStatus(claimProcessDTO.getStatus());
            if (claim.getClaimStatus().equals("0")) {
                throw new RuntimeException("认领请求状态不合法");
            }else if (claim.getClaimStatus().equals("1")) {
                // 认领请求被批准，更新失物状态
                LostOrFoundItem lostOrFoundItem =lostOrFoundItemMapper.selectById(claim.getLostOrFoundItemId());
                if (lostOrFoundItem != null&&lostOrFoundItem.getStatus().equals("0")) {
                    lostOrFoundItem.setStatus(1); // 1表示已认领/已领取
                    lostOrFoundItemMapper.update(lostOrFoundItem);
                }else{
                    throw new RuntimeException("失物状态不合法，无法认领");
                }
            }else if (claim.getClaimStatus().equals("2")) {
                throw new RuntimeException("认领请求被拒绝");
            }
            claim.setConfirmTime(LocalDateTime.now());
            claimMapper.updateClaimRecord(claim);
        }else{
            throw new RuntimeException("无权限处理该认领请求或请求不存在");
        }
    }

    @Override
    public String getClaimRequest(Long requestId) {
        Claim claim = claimMapper.getClaimById(requestId);
        if (claim != null) {
            return claim.getClaimStatus();
        } else {
            throw new RuntimeException("认领请求不存在");
        }
    }

    @Override
    public void uploadchatUser(Long fromId, Long toId) {
        chatUserMapper.insertChatUser(fromId,toId);
        chatUserMapper.insertChatUser(toId,fromId);
    }

    @Override
    public PageResult seekChatuser(ChatUserDTO chatUserDTO) {
        PageHelper.startPage(chatUserDTO.getPage(), chatUserDTO.getPageSize());
        Page<Long> page = (Page<Long>) chatUserMapper.pageQuery(chatUserDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }


}
