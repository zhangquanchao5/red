package com.red.dao;

import com.red.domain.UserHistory;

public interface UserHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserHistory record);

    int insertSelective(UserHistory record);

    UserHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserHistory record);

    int updateByPrimaryKey(UserHistory record);
}