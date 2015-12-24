package com.red.dao;

import com.red.domain.OrgRule;

public interface OrgRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrgRule record);

    int insertSelective(OrgRule record);

    OrgRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrgRule record);

    int updateByPrimaryKey(OrgRule record);
}