package com.red.dao;

import com.red.common.apibean.OrgRuleReq;
import com.red.domain.OrgRule;

import java.util.List;

/**
 * The interface Org rule mapper.
 */
public interface OrgRuleMapper {
    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(OrgRule record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(OrgRule record);

    /**
     * Select by primary key org rule.
     *
     * @param id the id
     * @return the org rule
     */
    OrgRule selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(OrgRule record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(OrgRule record);

    /**
     * 获取所有有效的并且过期的红包规则
     *
     * @return the list
     */
    List<OrgRule> findExpireAndRunList();

    /**
     * 定时任务执行后，更改状态为已扫描
     *
     * @param orgRuleId the org rule id
     */
    void updateStatusHasScan(Integer orgRuleId);

    /**
     * 获取机构下的红包规则
     * @param orgId the org id
     * @return list
     */
    List<OrgRule> findByOrg(Integer orgId);

    /**
     * 获取查询条件下的红包规则
     *
     * @param orgRuleReq the org rule req
     * @return the list
     */
    List<OrgRule> findByQuery(OrgRuleReq orgRuleReq);
}