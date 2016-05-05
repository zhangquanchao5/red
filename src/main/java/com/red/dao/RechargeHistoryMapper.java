package com.red.dao;

import com.red.model.RechargeHistory;

import java.util.List;

public interface RechargeHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeHistory record);

    int insertSelective(RechargeHistory record);

    RechargeHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeHistory record);

    int updateByPrimaryKey(RechargeHistory record);

    List<RechargeHistory> findByRedId(Integer redId);
}