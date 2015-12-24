package com.red.dao;

import com.red.domain.OrgRule;

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
}