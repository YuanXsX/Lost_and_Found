package com.laf.mapper;

import com.laf.entity.Claim;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ClaimMapper {
    /**
     * 创建认领记录
     */
    @Insert("INSERT INTO chaim_records (lost_or_found_item_id, claimant_id, claim_status, apply_time, confirm_time, to_id) " +
            "VALUES (#{lostOrFoundItemId}, #{claimantId}, #{claimStatus}, NOW(), NULL, #{toId})")
    void createChaimRecord(Claim claim);

    /**
     * 根据ID获取未处理认领记录
     */
    @Select("SELECT * FROM chaim_records WHERE id = #{claimId} AND claim_status = '0'")
    Claim getClaimById(Long claimId);

    /**
     * 更新认领记录
     */
    @Update("UPDATE chaim_records SET claim_status = #{claimStatus}, confirm_time = NOW() WHERE id = #{id}")
    void updateClaimRecord(Claim claim);
}
