package com.red.dao;

import com.red.domain.RefundHistory;

public interface RefundHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RefundHistory record);

    int insertSelective(RefundHistory record);

    RefundHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefundHistory record);

    int updateByPrimaryKey(RefundHistory record);
}